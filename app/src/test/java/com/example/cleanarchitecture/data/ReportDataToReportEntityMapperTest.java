package com.example.cleanarchitecture.data;

import com.example.cleanarchitecture.data.api.datasource.ReportData;
import com.example.cleanarchitecture.data.entity.ReportEntity;
import com.example.cleanarchitecture.data.entity.mapper.ReportDataToReportEntityMapper;
import com.example.cleanarchitecture.fake.FakeNewsLocalAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReportDataToReportEntityMapperTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private ReportDataToReportEntityMapper reportDataToReportEntityMapper;

    @Before
    public void setUp() {
        reportDataToReportEntityMapper = new ReportDataToReportEntityMapper();
    }

    @Test
    public void givenTransformCollectionReportEntityCorrectly() {
        final String GIVEN_JSON_ARTICLES = FakeNewsLocalAPI.getJsonResponseNews();

        final Type typeReportData = new TypeToken<ArrayList<ReportData>>() {
        }.getType();
        ArrayList<ReportData> reportDataList = new Gson().fromJson(GIVEN_JSON_ARTICLES, typeReportData);
        Collection<ReportEntity> reportEntities = reportDataToReportEntityMapper.map(reportDataList);

        assertThat(reportEntities.size(), is(2));
        assertEntityNews(reportEntities);
    }

    @Test
    public void givenTransformReportEntityCorrectly() {
        final String FAKE_JSON_RESPONSE_TEAM = FakeNewsLocalAPI.getJsonResponseReport();

        final Type typeReportData = new TypeToken<ReportData>() {
        }.getType();

        ReportData reportData = new Gson().fromJson(FAKE_JSON_RESPONSE_TEAM, typeReportData);
        ReportEntity reportEntity = reportDataToReportEntityMapper.map(reportData);

        assertThat(reportEntity.getAuthor(), is("straitstimes"));
        //you can try test each attribute is possible
    }

    @Test
    public void givenReverseTransformCollectionReportEntityCorrectly() {
        Collection<ReportData> reportDataList = reportDataToReportEntityMapper
                .reverseMap(FakeNewsLocalAPI.getFakeReportEntityList());

        assertDataReport(reportDataList);
    }

    @Test
    public void givenReverseTransformReportEntityCorrectly() {
        ReportData reportData = reportDataToReportEntityMapper
                .reverseMap(FakeNewsLocalAPI.getFakeReportEntity());

        assertThat(reportData.getTitle(), is(FakeNewsLocalAPI.FAKE_REPORT_TITLE));
    }

    private void assertEntityNews(Collection<ReportEntity> news) {
        final ReportEntity reportEntityOne = ((ReportEntity) Objects.requireNonNull(news.toArray())[0]);
        final ReportEntity reportEntityTwo = ((ReportEntity) Objects.requireNonNull(news.toArray())[1]);
        assertThat(reportEntityOne.getAuthor(), is("nikkei"));
        assertThat(reportEntityTwo.getAuthor(), is("SeekingAlpha.com"));
    }

    private void assertDataReport(Collection<ReportData> news) {
        final ReportData reportDataOne = ((ReportData) Objects.requireNonNull(news.toArray())[0]);
        assertThat(reportDataOne.getTitle(), is(FakeNewsLocalAPI.FAKE_REPORT_TITLE));
    }
}

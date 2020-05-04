package com.example.cleanarchitecture.data;

import com.example.cleanarchitecture.data.entity.ReportEntity;
import com.example.cleanarchitecture.data.entity.mapper.ReportEntityToReportMapper;
import com.example.cleanarchitecture.domain.model.Report;
import com.example.cleanarchitecture.fake.FakeNewsLocalAPI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ReportEntityToReportMapperTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private ReportEntityToReportMapper reportEntityToReportMapper;

    @Before
    public void setUp() {
        reportEntityToReportMapper = new ReportEntityToReportMapper();
    }

    @Test
    public void givenTransformReportEntityToReport() {
        ReportEntity reportEntity = FakeNewsLocalAPI.getFakeReportEntity();
        Report report = reportEntityToReportMapper.map(reportEntity);
        assertThat(report, is(instanceOf(Report.class)));
        assertThat(report.getTitle(), is(FakeNewsLocalAPI.FAKE_REPORT_TITLE));
        assertThat(report.getDescription(), is(FakeNewsLocalAPI.FAKE_REPORT_DESCRIPTION));
    }

    @Test
    public void givenTransformReportToReportEntity() {
        Report report = FakeNewsLocalAPI.getFakeReport();
        ReportEntity reportEntity = reportEntityToReportMapper.reverseMap(report);
        assertThat(reportEntity, is(instanceOf(ReportEntity.class)));
        assertThat(reportEntity.getTitle(), is(FakeNewsLocalAPI.FAKE_REPORT_TITLE));
        assertThat(reportEntity.getDescription(), is(FakeNewsLocalAPI.FAKE_REPORT_DESCRIPTION));
    }
}

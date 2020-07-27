package com.ihariza.news.data.repository;

import com.ihariza.news.data.database.ReportDao;
import com.ihariza.news.data.repository.local.LocalNewsRepositoryImp;
import com.ihariza.news.fake.FakeNewsLocalAPI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.rxjava3.core.Single;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class LocalNewsRepositoryTest {


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ReportDao reportDao;

    private LocalNewsRepositoryImp localNewsRepository;

    @Before
    public void setup() {
        localNewsRepository = new LocalNewsRepositoryImp(reportDao);
    }

    @Test
    public void givenPageNumberShouldReturnsReportListEntity() {
        given(localNewsRepository.getNews(1))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntityList()));

        localNewsRepository.getNews(1);

        verify(reportDao).getAll(1);
    }

    @Test
    public void givenReportIdShouldReturnsReportEntity() {
        given(localNewsRepository.getReport(FakeNewsLocalAPI.REPORT_ID))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntity()));

        localNewsRepository.getReport(FakeNewsLocalAPI.REPORT_ID);

        verify(reportDao).findBy(FakeNewsLocalAPI.REPORT_ID);
    }
}

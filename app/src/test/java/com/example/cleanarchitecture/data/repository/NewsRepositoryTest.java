package com.example.cleanarchitecture.data.repository;

import com.example.cleanarchitecture.data.entity.mapper.ReportEntityToReportMapper;
import com.example.cleanarchitecture.data.repository.local.LocalNewsRepository;
import com.example.cleanarchitecture.data.repository.remote.RemoteNewsRepository;
import com.example.cleanarchitecture.domain.repository.NewsRepository;
import com.example.cleanarchitecture.fake.FakeNewsLocalAPI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.rxjava3.core.Single;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NewsRepositoryTest {

    private static final String REPORT_ID = "1";

    @Mock
    private LocalNewsRepository localNewsRepository;

    @Mock
    private RemoteNewsRepository remoteNewsRepository;

    private NewsRepository newsRepository;

    @Before
    public void setUp() {
        newsRepository = new NewsRepositoryImp(localNewsRepository, remoteNewsRepository,
                new ReportEntityToReportMapper());
    }

    @Test
    public void givenReportEntityListFromLocalApi() {
        given(localNewsRepository.getNews()).willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntityList()));

        localNewsRepository.getNews();

        verify(localNewsRepository).getNews();
    }

    @Test
    public void givenReportEntityByIdFromLocalApi() {
        given(localNewsRepository.getReport(REPORT_ID)).willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntity()));

        newsRepository.getReport(REPORT_ID);

        verify(localNewsRepository).getReport(REPORT_ID);
    }
}

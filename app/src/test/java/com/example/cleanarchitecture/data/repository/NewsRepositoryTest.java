package com.example.cleanarchitecture.data.repository;

import com.example.cleanarchitecture.data.entity.mapper.ReportEntityToReportMapper;
import com.example.cleanarchitecture.data.repository.local.LocalNewsRepository;
import com.example.cleanarchitecture.data.repository.remote.RemoteNewsRepository;
import com.example.cleanarchitecture.domain.repository.NewsRepository;
import com.example.cleanarchitecture.fake.FakeNewsLocalAPI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class NewsRepositoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private LocalNewsRepository localNewsRepository;

    @Mock
    private RemoteNewsRepository remoteNewsRepository;

    @InjectMocks
    private ReportEntityToReportMapper reportMapper;

    private NewsRepository newsRepository;

    @Before
    public void setUp() {
        newsRepository = new NewsRepositoryImp(
                localNewsRepository,
                remoteNewsRepository,
                reportMapper);
    }

    @Test
    public void givenReportEntityByIdFromLocalApiShouldReturnsReportEntiity() {
        given(localNewsRepository.getReport(FakeNewsLocalAPI.REPORT_ID))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntity()));

        newsRepository.getReport(FakeNewsLocalAPI.REPORT_ID);

        verify(localNewsRepository).getReport(FakeNewsLocalAPI.REPORT_ID);
    }

    @Test
    public void givenReportEntityListFromRepositoryShouldReturnsReportList() {
        given(remoteNewsRepository.getNews(1))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntityList()));
        given(localNewsRepository.getNews(1))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReportEntityList()));
        given(localNewsRepository.removeAll())
                .willReturn(Completable.complete());
        given(localNewsRepository.save(any()))
                .willReturn(Completable.complete());

        newsRepository.getNews(1)
                .test()
                .assertNoErrors()
                .assertValue(reportList ->
                        reportList.get(0).getId()
                                .equals(FakeNewsLocalAPI.getFakeReportList().get(0).getId()))
                .assertValue(reportList ->
                        reportList.size() == FakeNewsLocalAPI.getFakeReportList().size())
                .dispose();

        verify(localNewsRepository).getNews(1);
    }
}

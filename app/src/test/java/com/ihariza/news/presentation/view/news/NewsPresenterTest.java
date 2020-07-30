package com.ihariza.news.presentation.view.news;

import com.ihariza.news.domain.usecase.GetNewsUseCase;
import com.ihariza.news.fake.FakeNewsLocalAPI;
import com.ihariza.news.presentation.model.mapper.ReportToReportDtoMapper;
import com.ihariza.news.presentation.view.util.Constants;
import com.ihariza.news.scheduler.SchedulerProviderImp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import io.reactivex.rxjava3.core.Single;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class NewsPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private NewsContract.View view;
    @Mock
    private GetNewsUseCase getNewsUseCase;
    @InjectMocks
    private SchedulerProviderImp schedulerProvider;
    @InjectMocks
    private ReportToReportDtoMapper reportToReportDtoMapper;

    private NewsPresenter newsPresenter;

    @Before
    public void setUp() {
        newsPresenter = new NewsPresenter(view, schedulerProvider, getNewsUseCase, reportToReportDtoMapper);
    }

    @Test
    public void showNewsWhenRetrieveNewsSuccessfully() {
        given(getNewsUseCase.getNews(1))
                .willReturn(Single.just(Collections.emptyList()));

        newsPresenter.start();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showLoading();
        order.verify(view).showNews(Collections.emptyList());
        order.verify(view).hideLoading();

        verifyNoMoreInteractions(view);
    }

    @Test
    public void showErrorWhenRetrieveNewsFails() {
        given(getNewsUseCase.getNews(1))
                .willReturn(Single.error(new Throwable("Error getting news")));

        newsPresenter.start();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showLoading();
        order.verify(view).showError("Error getting news");
        order.verify(view).hideLoading();

        verifyNoMoreInteractions(view);
    }

    @Test
    public void refreshNewsShouldReturnsReportList() {
        given(getNewsUseCase.getNews(anyInt()))
                .willReturn(Single.just(Collections.emptyList()));

        newsPresenter.refreshNews();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showRefreshedNews(Collections.emptyList());
    }

    @Test
    public void openReportShouldShowReportView() {
        newsPresenter.openReport(FakeNewsLocalAPI.getFakeReportDto());
        verify(view).openReport(FakeNewsLocalAPI.FAKE_REPORT_ID);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenGetNewsIsLastPageShouldReturnTrue() {
        given(getNewsUseCase.getNews(1))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReportList(1)));

        newsPresenter.start();

        assertTrue(newsPresenter.isNewsLastPage());
    }

    @Test
    public void whenGetNewsIsNotLastPageShouldReturnFalse() {
        given(getNewsUseCase.getNews(1))
                .willReturn(Single.just(
                        FakeNewsLocalAPI.getFakeReportList(Constants.NEWS_PAGE_SIZE)));

        newsPresenter.start();

        assertFalse(newsPresenter.isNewsLastPage());
    }
}

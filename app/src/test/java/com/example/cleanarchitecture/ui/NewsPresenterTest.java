package com.example.cleanarchitecture.ui;

import com.example.cleanarchitecture.domain.usecase.GetNewsUseCase;
import com.example.cleanarchitecture.fake.FakeNewsLocalAPI;
import com.example.cleanarchitecture.presentation.model.mapper.ReportToReportDtoMapper;
import com.example.cleanarchitecture.presentation.view.news.NewsContract;
import com.example.cleanarchitecture.presentation.view.news.NewsPresenter;
import com.example.cleanarchitecture.scheduler.SchedulerProviderImp;

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

import static org.mockito.BDDMockito.given;
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
    @Mock
    private ReportToReportDtoMapper reportToReportDtoMapper;

    private NewsPresenter newsPresenter;

    @Before
    public void setUp() {
        newsPresenter = new NewsPresenter(view, schedulerProvider, getNewsUseCase, reportToReportDtoMapper);
    }

    @Test
    public void showNewsWhenRetrieveNewsSuccessfully() {
        given(getNewsUseCase.getNews()).willReturn(Single.just(FakeNewsLocalAPI.getFakeReportList()));

        newsPresenter.start();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showLoading();
        order.verify(view).showNews(Collections.emptyList());
        order.verify(view).hideLoading();

        verifyNoMoreInteractions(view);
    }

    @Test
    public void showErrorWhenRetrieveNewsFails() {
        given(getNewsUseCase.getNews()).willReturn(Single.error(new Throwable("Error getting news")));

        newsPresenter.start();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showLoading();
        order.verify(view).showError("Error getting news");
        order.verify(view).hideLoading();

        verifyNoMoreInteractions(view);
    }
}

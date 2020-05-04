package com.example.cleanarchitecture.ui;

import com.example.cleanarchitecture.domain.usecase.GetReportUseCase;
import com.example.cleanarchitecture.fake.FakeNewsLocalAPI;
import com.example.cleanarchitecture.presentation.model.mapper.ReportToReportDtoMapper;
import com.example.cleanarchitecture.presentation.view.report.ReportContract;
import com.example.cleanarchitecture.presentation.view.report.ReportPresenter;
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

import io.reactivex.rxjava3.core.Single;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ReportPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ReportContract.View view;
    @Mock
    private GetReportUseCase getReportUseCase;
    @InjectMocks
    private SchedulerProviderImp schedulerProvider;
    @InjectMocks
    private ReportToReportDtoMapper reportToReportDtoMapper;

    private ReportPresenter reportPresenter;

    @Before
    public void setUp() {
        reportPresenter = new ReportPresenter(view, schedulerProvider, getReportUseCase, reportToReportDtoMapper);
    }

    @Test
    public void showReportWhenRetrieveReportSuccessfully() {
        given(getReportUseCase.getReport(any()))
                .willReturn(Single.just(FakeNewsLocalAPI.getFakeReport()));

        reportPresenter.setReportId(FakeNewsLocalAPI.getFakeReportDto().getId());
        reportPresenter.start();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showTitle(FakeNewsLocalAPI.getFakeReport().getTitle());
        order.verify(view).showSubtitle(FakeNewsLocalAPI.getFakeReport().getAuthor());
        order.verify(view).showReport(FakeNewsLocalAPI.getFakeReport().getUrl());

        verifyNoMoreInteractions(view);
    }

    @Test
    public void showErrorWhenRetrieveReportFails() {
        given(getReportUseCase.getReport(any()))
                .willReturn(Single.error(new Throwable("Error getting report")));

        reportPresenter.setReportId(FakeNewsLocalAPI.getFakeReportDto().getId());
        reportPresenter.start();

        InOrder order = Mockito.inOrder(view);
        order.verify(view).showError("Error getting report");

        verifyNoMoreInteractions(view);
    }
}

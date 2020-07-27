package com.ihariza.news.presentation.view.report;

import com.ihariza.news.domain.scheduler.SchedulerProvider;
import com.ihariza.news.domain.usecase.GetReportUseCase;
import com.ihariza.news.presentation.model.ReportDto;
import com.ihariza.news.presentation.model.mapper.ReportToReportDtoMapper;
import com.ihariza.news.presentation.view.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;

public class ReportPresenter extends BasePresenter<ReportContract.View>
        implements ReportContract.Presenter {

    private ReportToReportDtoMapper reportToReportDtoMapper;
    private SchedulerProvider schedulerProvider;
    private GetReportUseCase getReportUseCase;
    private String reportId;
    private ReportDto report;

    @Inject
    public ReportPresenter(ReportContract.View view,
                           SchedulerProvider schedulerProvider,
                           GetReportUseCase getReportUseCase,
                           ReportToReportDtoMapper reportToReportDtoMapper) {
        super(view);
        this.schedulerProvider = schedulerProvider;
        this.getReportUseCase = getReportUseCase;
        this.reportToReportDtoMapper = reportToReportDtoMapper;
    }

    @Override
    public void start() {
        getReport(reportId);
    }

    private void getReport(String reportId) {
        Disposable disposable = getReportUseCase.getReport(reportId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map(reportToReportDtoMapper::map)
                .subscribe(report -> {
                            this.report = report;
                            view.showTitle(report.getTitle());
                            view.showSubtitle(report.getAuthor());
                            view.showReport(report.getUrl());
                        },
                        throwable -> view.showError(throwable.getMessage())
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void setReportId(String id) {
        this.reportId = id;
    }

    @Override
    public void shareReport() {
        if (report != null) {
            view.showShareReport(report);
        }
    }

    @Override
    public void openReport() {
        if (report != null && report.getUrl() != null) {
            view.showOpenReport(report.getUrl());
        }
    }
}

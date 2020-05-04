package com.example.cleanarchitecture.presentation.view.news;

import com.example.cleanarchitecture.domain.scheduler.SchedulerProvider;
import com.example.cleanarchitecture.domain.usecase.GetNewsUseCase;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.model.mapper.ReportToReportDtoMapper;
import com.example.cleanarchitecture.presentation.view.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;

public class NewsPresenter extends BasePresenter<NewsContract.View>
        implements NewsContract.Presenter {

    private GetNewsUseCase getNewsUseCase;
    private ReportToReportDtoMapper reportToReportDtoMapper;
    private SchedulerProvider schedulerProvider;

    @Inject
    public NewsPresenter(NewsContract.View view,
                         SchedulerProvider schedulerProvider,
                         GetNewsUseCase getNewsUseCase,
                         ReportToReportDtoMapper reportToReportDtoMapper) {
        super(view);
        this.schedulerProvider = schedulerProvider;
        this.getNewsUseCase = getNewsUseCase;
        this.reportToReportDtoMapper = reportToReportDtoMapper;
    }

    void onReportClicked(ReportDto report) {
        view.openReport(report.getId());
    }

    @Override
    public void start() {
        view.showLoading();
        getNews();
    }

    private void getNews() {
        Disposable disposable = getNewsUseCase.getNews()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map(reportToReportDtoMapper::map)
                .doAfterTerminate(() -> view.hideLoading())
                .subscribe(news ->
                                view.showNews(news),
                        throwable -> view.showError(throwable.getMessage()));
        compositeDisposable.add(disposable);
    }

}

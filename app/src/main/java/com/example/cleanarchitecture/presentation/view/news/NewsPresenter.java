package com.example.cleanarchitecture.presentation.view.news;

import com.example.cleanarchitecture.domain.scheduler.SchedulerProvider;
import com.example.cleanarchitecture.domain.usecase.GetNewsUseCase;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.model.mapper.ReportToReportDtoMapper;
import com.example.cleanarchitecture.presentation.view.base.BasePresenter;
import com.example.cleanarchitecture.presentation.view.util.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;

public class NewsPresenter extends BasePresenter<NewsContract.View>
        implements NewsContract.Presenter, NewsContract.ReportListener, NewsContract.Pagination {

    private GetNewsUseCase getNewsUseCase;
    private ReportToReportDtoMapper reportToReportDtoMapper;
    private SchedulerProvider schedulerProvider;
    private int pageNumber;
    private boolean isLoading;
    private boolean isLastPage;

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

    @Override
    public void start() {
        pageNumber = 1;
        loadNewsPage();
    }

    @Override
    public void onReportClicked(ReportDto report) {
        view.openReport(report.getId());
    }

    @Override
    public void loadNewsPage() {
        showLoading();
        getNews(false);
    }

    @Override
    public void refreshNews() {
        pageNumber = 1;
        getNews(true);
    }

    private void getNews(boolean refresh) {
        Disposable disposable = getNewsUseCase.getNews(pageNumber)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map(reportToReportDtoMapper::map)
                .doAfterTerminate(() -> hideLoading(refresh))
                .subscribe(news -> {
                            pageNumber++;
                            isLoading = false;
                            isLastPage = news.size() < Constants.NEWS_PAGE_SIZE;
                            showNews(news, refresh);
                        },
                        throwable -> {
                            isLoading = false;
                            view.showError(throwable.getMessage());
                        });
        compositeDisposable.add(disposable);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean isLastPage() {
        return isLastPage;
    }

    private void showNews(List<ReportDto> news, boolean refresh) {
        if (refresh) {
            view.showRefreshedNews(news);
        } else {
            view.showNews(news);
        }
    }

    private void showLoading() {
        isLoading = true;
        view.showLoading();
    }

    private void hideLoading(boolean refresh) {
        if (!refresh) {
            isLoading = false;
            view.hideLoading();
        }
    }

}

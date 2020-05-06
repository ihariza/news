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
        implements NewsContract.Presenter, NewsContract.ReportListener {

    private GetNewsUseCase getNewsUseCase;
    private ReportToReportDtoMapper reportToReportDtoMapper;
    private SchedulerProvider schedulerProvider;
    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage;

    @Inject
    public NewsPresenter(NewsContract.View view,
                         SchedulerProvider schedulerProvider,
                         GetNewsUseCase getNewsUseCase,
                         ReportToReportDtoMapper reportToReportDtoMapper) {
        super(view);
        this.schedulerProvider = schedulerProvider;
        this.getNewsUseCase = getNewsUseCase;
        this.reportToReportDtoMapper = reportToReportDtoMapper;
        currentPage = Constants.START_NEWS_PAGE;
    }

    @Override
    public void start() {
        getNewsPage(currentPage);
    }

    @Override
    public void onReportClicked(ReportDto report) {
        view.openReport(report.getId());
    }

    @Override
    public void getNewsPage(int pageNumber) {
        view.showLoading();
        currentPage = pageNumber;
        getNews( false);
    }

    @Override
    public void refreshNews() {
        currentPage = Constants.START_NEWS_PAGE;
        getNews(true);
    }

    private void getNews(boolean refresh) {
        isLoading = true;
        Disposable disposable = getNewsUseCase.getNews(currentPage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map(reportToReportDtoMapper::map)
                .doAfterTerminate(() -> hideLoading(refresh))
                .subscribe(news -> {
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

    private void hideLoading(boolean refresh) {
        isLoading = false;
        if (refresh) {
            view.hideRefresh();
        } else {
            view.hideLoading();
        }
    }

}

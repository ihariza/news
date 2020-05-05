package com.example.cleanarchitecture.presentation.view.news;

import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BasePresenterContract;
import com.example.cleanarchitecture.presentation.view.base.BaseViewContract;

import java.util.List;

public interface NewsContract {

    interface View extends BaseViewContract {

        void showLoading();

        void hideLoading();

        void hideRefresh();

        void showError(String message);

        void showNews(List<ReportDto> news);

        void showRefreshedNews(List<ReportDto> news);

        void openReport(String reportId);
    }

    interface Presenter extends BasePresenterContract {

        void refreshNews();

        void loadNewsPage();

        boolean isLoading();

        boolean isLastPage();
    }

    interface ReportListener {

        void onReportClicked(ReportDto report);
    }

}

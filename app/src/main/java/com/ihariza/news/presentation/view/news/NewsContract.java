package com.ihariza.news.presentation.view.news;

import com.ihariza.news.presentation.model.ReportDto;
import com.ihariza.news.presentation.view.base.BasePresenterContract;
import com.ihariza.news.presentation.view.base.BaseViewContract;

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

        void getNewsPage(int pageNumber);

        void refreshNews();

        void openReport(ReportDto report);

        boolean isNewsLoading();

        boolean isNewsLastPage();
    }

}

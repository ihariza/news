package com.ihariza.news.presentation.view.report;

import com.ihariza.news.presentation.model.ReportDto;
import com.ihariza.news.presentation.view.base.BasePresenterContract;
import com.ihariza.news.presentation.view.base.BaseViewContract;

public interface ReportContract {

    interface View extends BaseViewContract {

        void showError(String message);

        void showTitle(String title);

        void showSubtitle(String subtitle);

        void showReport(String url);

        void showShareReport(ReportDto report);

        void showOpenReport(String url);
    }

    interface Presenter extends BasePresenterContract {

        void setReportId(String id);

        void shareReport();

        void openReport();
    }
}

package com.example.cleanarchitecture.presentation.view.report;

import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BasePresenterContract;
import com.example.cleanarchitecture.presentation.view.base.BaseViewContract;

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

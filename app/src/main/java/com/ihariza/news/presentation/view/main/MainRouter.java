package com.ihariza.news.presentation.view.main;

import androidx.appcompat.app.AppCompatActivity;

import com.ihariza.news.presentation.view.base.BaseRouter;
import com.ihariza.news.presentation.view.news.NewsFragment;
import com.ihariza.news.presentation.view.report.ReportFragment;

public class MainRouter extends BaseRouter implements MainRouterContract {

    MainRouter(AppCompatActivity activity, int containerId) {
        super(activity, containerId);
    }

    @Override
    public void showNews() {
        show(new NewsFragment(), true);
    }

    @Override
    public void showReport(String reportId) {
        show(ReportFragment.newInstance(reportId), true);
    }

}

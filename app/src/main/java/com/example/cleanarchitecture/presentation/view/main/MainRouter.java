package com.example.cleanarchitecture.presentation.view.main;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cleanarchitecture.presentation.view.base.BaseRouter;
import com.example.cleanarchitecture.presentation.view.news.NewsFragment;
import com.example.cleanarchitecture.presentation.view.report.ReportFragment;

public class MainRouter extends BaseRouter implements MainRouterContract {

    public MainRouter(AppCompatActivity activity, int containerId) {
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

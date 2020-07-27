package com.ihariza.news.presentation.view.main;

import com.ihariza.news.presentation.view.base.BaseRouterContract;

public interface MainRouterContract extends BaseRouterContract {

    void showNews();

    void showReport(String reportId);
}

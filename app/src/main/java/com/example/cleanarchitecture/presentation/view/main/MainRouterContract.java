package com.example.cleanarchitecture.presentation.view.main;

import com.example.cleanarchitecture.presentation.view.base.BaseRouterContract;

public interface MainRouterContract extends BaseRouterContract {

    void showNews();

    void showReport(String reportId);
}

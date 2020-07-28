package com.ihariza.news.injection.modules.fragment;

import com.ihariza.news.presentation.view.report.ReportContract;
import com.ihariza.news.presentation.view.report.ReportFragment;
import com.ihariza.news.presentation.view.report.ReportPresenter;

import dagger.Binds;
import dagger.Module;

@Module
interface ReportFragmentModule {
    @Binds
    ReportContract.View bindReportFragment(ReportFragment fragment);

    @Binds
    ReportContract.Presenter bindReportPresenter(ReportPresenter presenter);
}

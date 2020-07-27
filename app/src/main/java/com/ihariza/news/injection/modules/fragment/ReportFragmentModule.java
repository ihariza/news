package com.ihariza.news.injection.modules.fragment;

import com.ihariza.news.presentation.view.report.ReportContract;
import com.ihariza.news.presentation.view.report.ReportFragment;
import com.ihariza.news.presentation.view.report.ReportPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ReportFragmentModule {
    @Binds
    abstract ReportContract.View bindReportFragment(ReportFragment fragment);

    @Binds
    abstract ReportContract.Presenter bindReportPresenter(ReportPresenter presenter);
}

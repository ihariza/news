package com.example.cleanarchitecture.injection.modules.fragment;

import com.example.cleanarchitecture.presentation.view.report.ReportContract;
import com.example.cleanarchitecture.presentation.view.report.ReportFragment;
import com.example.cleanarchitecture.presentation.view.report.ReportPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ReportFragmentModule {
    @Binds
    abstract ReportContract.View bindReportFragment(ReportFragment fragment);

    @Binds
    abstract ReportContract.Presenter bindReportPresenter(ReportPresenter presenter);
}

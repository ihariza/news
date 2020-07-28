package com.ihariza.news.injection.modules.fragment;

import com.ihariza.news.injection.modules.ManagersModule;
import com.ihariza.news.injection.scopes.PerFragment;
import com.ihariza.news.presentation.view.news.NewsFragment;
import com.ihariza.news.presentation.view.report.ReportFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector(modules = {NewFragmentModule.class, ManagersModule.class})
    NewsFragment bindNewsFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {ReportFragmentModule.class, ManagersModule.class})
    ReportFragment bindReportFragment();

}

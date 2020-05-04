package com.example.cleanarchitecture.injection.modules.fragment;

import com.example.cleanarchitecture.injection.scopes.PerFragment;
import com.example.cleanarchitecture.presentation.view.news.NewsFragment;
import com.example.cleanarchitecture.presentation.view.report.ReportFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector(modules = {NewFragmentModule.class})
    abstract NewsFragment bindNewsFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {ReportFragmentModule.class})
    abstract ReportFragment bindReportFragment();

}

package com.example.cleanarchitecture.injection.modules;

import android.content.Context;

import com.example.cleanarchitecture.NewsApplication;
import com.example.cleanarchitecture.domain.scheduler.SchedulerProvider;
import com.example.cleanarchitecture.injection.modules.activity.ActivityBindingModule;
import com.example.cleanarchitecture.injection.modules.fragment.FragmentBindingModule;
import com.example.cleanarchitecture.injection.scopes.PerApplication;
import com.example.cleanarchitecture.presentation.scheduler.SchedulerProviderImp;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;


@Module(includes = {AndroidSupportInjectionModule.class, ActivityBindingModule.class,
        FragmentBindingModule.class})
public class ApplicationModule {

    private final NewsApplication newsApplication;

    public ApplicationModule(NewsApplication newsApplication) {
        this.newsApplication = newsApplication;
    }

    @Provides
    @PerApplication
    Context provideApplicationContext() {
        return newsApplication;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider(SchedulerProviderImp schedulerProviderImp) {
        return schedulerProviderImp;
    }
}

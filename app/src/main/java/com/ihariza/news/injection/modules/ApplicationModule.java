package com.ihariza.news.injection.modules;

import android.content.Context;

import com.ihariza.news.NewsApplication;
import com.ihariza.news.domain.scheduler.SchedulerProvider;
import com.ihariza.news.injection.modules.activity.ActivityBindingModule;
import com.ihariza.news.injection.modules.fragment.FragmentBindingModule;
import com.ihariza.news.injection.scopes.PerApplication;
import com.ihariza.news.presentation.scheduler.SchedulerProviderImp;

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
    @PerApplication
    SchedulerProvider provideSchedulerProvider(SchedulerProviderImp schedulerProviderImp) {
        return schedulerProviderImp;
    }
}

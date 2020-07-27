package com.ihariza.news.presentation.scheduler;

import com.ihariza.news.domain.scheduler.SchedulerProvider;
import com.ihariza.news.injection.scopes.PerApplication;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@PerApplication
public class SchedulerProviderImp implements SchedulerProvider {

    @Inject
    SchedulerProviderImp() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}

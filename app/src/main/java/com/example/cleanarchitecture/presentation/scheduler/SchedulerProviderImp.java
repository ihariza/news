package com.example.cleanarchitecture.presentation.scheduler;

import com.example.cleanarchitecture.domain.scheduler.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@Singleton
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

package com.ihariza.news.scheduler;

import com.ihariza.news.domain.scheduler.SchedulerProvider;
import com.ihariza.news.injection.scopes.PerApplication;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@PerApplication
public class SchedulerProviderImp implements SchedulerProvider {

    @Inject
    public SchedulerProviderImp() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}

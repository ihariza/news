package com.example.cleanarchitecture.domain.scheduler;


import io.reactivex.rxjava3.core.Scheduler;

/**
 * Thread abstraction created to change the execution context from any thread to any other thread.
 */
public interface SchedulerProvider {

    Scheduler io();

    Scheduler ui();

}

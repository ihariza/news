package com.ihariza.news.presentation.view.util;

import androidx.test.espresso.idling.CountingIdlingResource;

public class RequestCountingIdlingResource {

    private RequestCountingIdlingResource() {
        throw new IllegalStateException("Test utility class");
    }

    private static CountingIdlingResource countingIdlingResource;

    public static CountingIdlingResource getCountingIdlingResource() {
        if (countingIdlingResource == null) {
            countingIdlingResource = new CountingIdlingResource("GLOBAL");
        }
        return countingIdlingResource;
    }

    public static void increment() {
        getCountingIdlingResource().increment();
    }

    public static void decrement() {
        if (!getCountingIdlingResource().isIdleNow()) {
            getCountingIdlingResource().decrement();
        }
    }
}

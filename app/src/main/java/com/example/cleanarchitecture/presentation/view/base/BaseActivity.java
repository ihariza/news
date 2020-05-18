package com.example.cleanarchitecture.presentation.view.base;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * <p>
 * BaseActivity contains some modifications to the native AppCompatActivity.
 * Mainly, it use ButterKnife for view binding and it automatically check if
 * toolbar exists.
 * </p>
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewBinding().getRoot());
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract ViewBinding getViewBinding();

    public abstract <T extends BaseRouterContract> T getRouter();
}
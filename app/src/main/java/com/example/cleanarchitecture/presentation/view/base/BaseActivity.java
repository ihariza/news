package com.example.cleanarchitecture.presentation.view.base;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import com.example.cleanarchitecture.presentation.view.main.MainRouter;
import com.example.cleanarchitecture.presentation.view.main.MainRouterContract;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * <p>
 * BaseActivity contains some modifications to the native AppCompatActivity.
 * Mainly, it use ButterKnife for view binding and it automatically check if
 * toolbar exists.
 * </p>
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    private MainRouterContract navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewBinding().getRoot());
        navigation = new MainRouter(this, geContainerId());
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract ViewBinding getViewBinding();

    protected abstract int geContainerId();

    public MainRouterContract getNavigation() {
        return navigation;
    }
}
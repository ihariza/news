package com.ihariza.news.presentation.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import dagger.android.support.DaggerFragment;


public abstract class BaseFragment extends DaggerFragment {

    protected Context context;
    protected BaseActivity baseActivity;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layoutRes(), container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.baseActivity = (BaseActivity)context;

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onBackPressed();
            }
        };
        baseActivity.getOnBackPressedDispatcher().addCallback(this, callback);
    }

    protected <T extends BaseRouterContract> T getRouter() {
        return baseActivity.getRouter();
    }

    protected void onBackPressed() {
        getRouter().backView();
    }

}

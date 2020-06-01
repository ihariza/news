package com.example.cleanarchitecture.presentation.view.main;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.ActivityMainBinding;
import com.example.cleanarchitecture.presentation.view.base.BaseActivity;
import com.example.cleanarchitecture.presentation.view.base.BaseRouterContract;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    private ActivityMainBinding binding;
    private MainRouterContract router;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        router = new MainRouter(this, R.id.container);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            presenter.start();
        }
    }

    @Override
    protected ViewBinding getViewBinding() {
        return binding;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseRouterContract> T getRouter() {
        return (T) router;
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void showNews() {
        router.showNews();
    }
}

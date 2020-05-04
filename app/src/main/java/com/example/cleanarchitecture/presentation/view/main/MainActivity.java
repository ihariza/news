package com.example.cleanarchitecture.presentation.view.main;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.ActivityMainBinding;
import com.example.cleanarchitecture.presentation.view.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    protected ViewBinding getViewBinding() {
        return binding;
    }

    @Override
    protected int geContainerId() {
        return R.id.container;
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
        getNavigation().showNews();
    }
}

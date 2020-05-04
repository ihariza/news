package com.example.cleanarchitecture.injection.modules.activity;

import com.example.cleanarchitecture.presentation.view.main.MainActivity;
import com.example.cleanarchitecture.presentation.view.main.MainContract;
import com.example.cleanarchitecture.presentation.view.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class MainActivityModule {
    @Binds
    abstract MainContract.View bindMainActivity(MainActivity activity);

    @Binds
    abstract MainContract.Presenter bindMainPresenter(MainPresenter presenter);
}

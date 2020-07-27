package com.ihariza.news.injection.modules.activity;

import com.ihariza.news.presentation.view.main.MainActivity;
import com.ihariza.news.presentation.view.main.MainContract;
import com.ihariza.news.presentation.view.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class MainActivityModule {
    @Binds
    abstract MainContract.View bindMainActivity(MainActivity activity);

    @Binds
    abstract MainContract.Presenter bindMainPresenter(MainPresenter presenter);

}

package com.ihariza.news.injection.modules.activity;

import com.ihariza.news.presentation.view.main.MainActivity;
import com.ihariza.news.presentation.view.main.MainContract;
import com.ihariza.news.presentation.view.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
interface MainActivityModule {
    @Binds
    MainContract.View bindMainActivity(MainActivity activity);

    @Binds
    MainContract.Presenter bindMainPresenter(MainPresenter presenter);

}

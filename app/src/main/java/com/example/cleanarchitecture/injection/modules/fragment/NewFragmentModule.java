package com.example.cleanarchitecture.injection.modules.fragment;

import com.example.cleanarchitecture.presentation.view.news.NewsContract;
import com.example.cleanarchitecture.presentation.view.news.NewsFragment;
import com.example.cleanarchitecture.presentation.view.news.NewsPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class NewFragmentModule {
    @Binds
    abstract NewsContract.View bindNewsFragment(NewsFragment fragment);

    @Binds
    abstract NewsContract.Presenter bindNewsPresenter(NewsPresenter presenter);
}

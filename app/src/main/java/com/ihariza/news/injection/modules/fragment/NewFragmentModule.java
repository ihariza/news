package com.ihariza.news.injection.modules.fragment;

import com.ihariza.news.presentation.view.news.NewsContract;
import com.ihariza.news.presentation.view.news.NewsFragment;
import com.ihariza.news.presentation.view.news.NewsPresenter;

import dagger.Binds;
import dagger.Module;

@Module
interface NewFragmentModule {
    @Binds
    NewsContract.View bindNewsFragment(NewsFragment fragment);

    @Binds
    NewsContract.Presenter bindNewsPresenter(NewsPresenter presenter);
    
}

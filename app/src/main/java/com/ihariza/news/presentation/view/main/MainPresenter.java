package com.ihariza.news.presentation.view.main;

import com.ihariza.news.presentation.view.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    @Inject
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        view.showNews();
    }

}

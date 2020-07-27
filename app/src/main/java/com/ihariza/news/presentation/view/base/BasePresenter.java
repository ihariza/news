package com.ihariza.news.presentation.view.base;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BasePresenter<V> implements BasePresenterContract {

    protected V view;
    protected final CompositeDisposable compositeDisposable;

    public BasePresenter(V view) {
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void stop() {
        compositeDisposable.clear();
    }

    public void destroy() {
        compositeDisposable.dispose();
    }

}

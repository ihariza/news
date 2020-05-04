package com.example.cleanarchitecture.presentation.view.main;

import com.example.cleanarchitecture.presentation.view.base.BasePresenterContract;
import com.example.cleanarchitecture.presentation.view.base.BaseViewContract;

public interface MainContract {

    interface View extends BaseViewContract {

        void showNews();
    }

    interface Presenter extends BasePresenterContract {

    }
}

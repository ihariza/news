package com.ihariza.news.presentation.view.main;

import com.ihariza.news.presentation.view.base.BasePresenterContract;
import com.ihariza.news.presentation.view.base.BaseViewContract;

public interface MainContract {

    interface View extends BaseViewContract {

        void showNews();
    }

    interface Presenter extends BasePresenterContract {

    }
}

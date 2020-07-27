package com.ihariza.news;

import androidx.multidex.MultiDexApplication;

import com.ihariza.news.injection.components.DaggerApplicationComponent;
import com.ihariza.news.injection.modules.ApplicationModule;
import com.ihariza.news.injection.modules.NetworkModule;
import com.ihariza.news.injection.modules.RepositoryModule;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class NewsApplication extends MultiDexApplication implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .repositoryModule(new RepositoryModule(this))
                .build()
                .inject(this);

        setupStetho();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    private void setupStetho() {
        if (BuildConfig.DEBUG && !isUnitTesting()) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public boolean isUnitTesting() {
        return false;
    }
}

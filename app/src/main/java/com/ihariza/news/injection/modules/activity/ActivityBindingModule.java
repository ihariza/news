package com.ihariza.news.injection.modules.activity;

import com.ihariza.news.injection.scopes.PerActivity;
import com.ihariza.news.presentation.view.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

}

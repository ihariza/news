package com.example.cleanarchitecture.injection.modules.activity;

import com.example.cleanarchitecture.injection.scopes.PerActivity;
import com.example.cleanarchitecture.presentation.view.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

}

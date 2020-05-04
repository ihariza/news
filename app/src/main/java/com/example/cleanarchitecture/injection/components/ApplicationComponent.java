package com.example.cleanarchitecture.injection.components;

import com.example.cleanarchitecture.NewsApplication;
import com.example.cleanarchitecture.injection.modules.ApplicationModule;
import com.example.cleanarchitecture.injection.modules.NetworkModule;
import com.example.cleanarchitecture.injection.modules.RepositoryModule;
import com.example.cleanarchitecture.injection.scopes.PerApplication;

import javax.inject.Singleton;

import dagger.Component;

@PerApplication
@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(NewsApplication app);
}
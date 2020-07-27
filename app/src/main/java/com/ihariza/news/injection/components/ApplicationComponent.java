package com.ihariza.news.injection.components;

import com.ihariza.news.NewsApplication;
import com.ihariza.news.injection.modules.ApplicationModule;
import com.ihariza.news.injection.modules.NetworkModule;
import com.ihariza.news.injection.modules.RepositoryModule;
import com.ihariza.news.injection.scopes.PerApplication;

import dagger.Component;

@PerApplication
@Component(modules = {ApplicationModule.class, RepositoryModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(NewsApplication app);
}
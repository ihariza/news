package com.example.cleanarchitecture.injection.modules;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagersModule {

    @Provides
    RequestManager provideGlideRequestManager(Context context) {
        return Glide.with(context);
    }
}

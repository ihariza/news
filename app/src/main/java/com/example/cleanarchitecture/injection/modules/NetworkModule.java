package com.example.cleanarchitecture.injection.modules;

import com.example.cleanarchitecture.data.api.NewsApi;
import com.example.cleanarchitecture.injection.scopes.PerApplication;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @PerApplication
    OkHttpClient providesOkHttpClient() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        clientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        clientBuilder.addInterceptor(httpLoggingInterceptor);
        clientBuilder.addInterceptor(new StethoInterceptor());

        return clientBuilder.build();
    }

    @Provides
    @PerApplication
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder.create();
    }

    @Provides
    @PerApplication
    Retrofit providesRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @PerApplication
    NewsApi providesNewsApi(Retrofit retrofit) {
        return retrofit.create(NewsApi.class);
    }
}

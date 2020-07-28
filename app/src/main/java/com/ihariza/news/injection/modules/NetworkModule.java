package com.ihariza.news.injection.modules;

import com.ihariza.news.BuildConfig;
import com.ihariza.news.data.api.NewsApi;
import com.ihariza.news.injection.scopes.PerApplication;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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

        URL baseURL = null;
        try {
            baseURL = new URL(baseUrl);
        } catch (MalformedURLException e) {
            Timber.d(e);
        }

        if (baseURL != null) {
            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add(baseURL.getHost(),
                            "sha256/" + BuildConfig.SPKI)
                    .build();
            clientBuilder.certificatePinner(certificatePinner);
        }

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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @PerApplication
    NewsApi providesNewsApi(Retrofit retrofit) {
        return retrofit.create(NewsApi.class);
    }
}

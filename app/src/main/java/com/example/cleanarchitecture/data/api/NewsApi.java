package com.example.cleanarchitecture.data.api;

import com.example.cleanarchitecture.data.api.datasource.NewsData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("latest-news?language=es")
    Single<NewsData> getLatestNews(@Query("apiKey") String apiKey);
}

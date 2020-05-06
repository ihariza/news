package com.example.cleanarchitecture.data.api;

import com.example.cleanarchitecture.data.api.datasource.NewsData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("latest-news")
    Single<NewsData> getLatestNews(@Query("apiKey") String apiKey,
                                   @Query("language") String language,
                                   @Query("country") String country,
                                   @Query("page_number") int pageNumber);
}

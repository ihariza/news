package com.ihariza.news.data.api;

import com.ihariza.news.data.api.datasource.NewsData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("latest-news")
    Single<NewsData> getLatestNews(@Query("apiKey") String apiKey,
                                   @Query("language") String language,
                                   @Query("country") String country,
                                   @Query("page_number") int pageNumber);
}

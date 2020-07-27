package com.ihariza.news.data.repository.remote;

import com.ihariza.news.data.entity.ReportEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RemoteNewsRepository {

    Single<List<ReportEntity>> getNews(int pageNumber);
}

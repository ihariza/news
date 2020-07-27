package com.ihariza.news.domain.repository;

import com.ihariza.news.domain.model.Report;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface NewsRepository {

    Single<List<Report>> getNews(int pageNumber);

    Single<Report> getReport(String id);

}

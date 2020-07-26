package com.example.cleanarchitecture.domain.repository;

import com.example.cleanarchitecture.domain.model.Report;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface NewsRepository {

    Single<List<Report>> getNews(int pageNumber);

    Single<Report> getReport(String id);

}

package com.example.cleanarchitecture.data.repository.local;

import com.example.cleanarchitecture.data.entity.ReportEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface LocalNewsRepository {

    Single<List<ReportEntity>> getNews(int pageNumber);

    Single<ReportEntity> getReport(String id);

    Completable save(ReportEntity report);

    Completable removeAll();
}

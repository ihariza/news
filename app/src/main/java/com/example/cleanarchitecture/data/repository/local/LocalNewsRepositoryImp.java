package com.example.cleanarchitecture.data.repository.local;

import com.example.cleanarchitecture.data.database.ReportDao;
import com.example.cleanarchitecture.data.entity.ReportEntity;

import java.util.List;

import javax.inject.Inject;

import hu.akarnokd.rxjava3.bridge.RxJavaBridge;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class LocalNewsRepositoryImp implements LocalNewsRepository{

    private final ReportDao reportDao;

    @Inject
    public LocalNewsRepositoryImp(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public Single<List<ReportEntity>> getNews(int pageNumber) {
        return RxJavaBridge.toV3Single(reportDao.getAll(pageNumber));
    }

    @Override
    public Single<ReportEntity> getReport(String id) {
        return RxJavaBridge.toV3Single(reportDao.findBy(id));
    }

    @Override
    public Completable save(ReportEntity report) {
        return RxJavaBridge.toV3Completable(reportDao.insert(report));
    }

    @Override
    public Completable removeAll() {
       return RxJavaBridge.toV3Completable(reportDao.deleteAll());
    }
}

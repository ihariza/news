package com.example.cleanarchitecture.data.repository.local;

import com.example.cleanarchitecture.data.database.ReportDao;
import com.example.cleanarchitecture.data.entity.ReportEntity;

import java.util.List;

import javax.inject.Inject;

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
        return reportDao.getAll(pageNumber);
    }

    @Override
    public Single<ReportEntity> getReport(String id) {
        return reportDao.findBy(id);
    }

    @Override
    public Completable save(ReportEntity report) {
        return reportDao.insert(report);
    }

    @Override
    public Completable removeAll() {
       return reportDao.deleteAll();
    }
}

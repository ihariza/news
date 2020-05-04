package com.example.cleanarchitecture.data.repository;

import com.example.cleanarchitecture.data.entity.mapper.ReportEntityToReportMapper;
import com.example.cleanarchitecture.data.repository.local.LocalNewsRepository;
import com.example.cleanarchitecture.data.repository.remote.RemoteNewsRepository;
import com.example.cleanarchitecture.domain.model.Report;
import com.example.cleanarchitecture.domain.repository.NewsRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


public class NewsRepositoryImp implements NewsRepository {

    private final LocalNewsRepository localNewsRepository;
    private final RemoteNewsRepository remoteNewsRepository;
    private final ReportEntityToReportMapper reportEntityToReportMapper;

    public NewsRepositoryImp(LocalNewsRepository localNewsRepository,
                             RemoteNewsRepository remoteNewsRepository,
                             ReportEntityToReportMapper reportEntityToReportMapper) {
        this.localNewsRepository = localNewsRepository;
        this.remoteNewsRepository = remoteNewsRepository;
        this.reportEntityToReportMapper = reportEntityToReportMapper;
    }

    @Override
    public Single<List<Report>> getNews(int pageNumber) {
        return remoteNewsRepository.getNews(pageNumber).map(reportEntities -> {
            localNewsRepository.removeAll().subscribe();
            localNewsRepository.saveAll(reportEntities).subscribe();
            return reportEntityToReportMapper.map(reportEntities);
        });
    }

    @Override
    public Single<Report> getReport(String id) {
        return localNewsRepository.getReport(id).map(reportEntityToReportMapper::map);
    }

    @Override
    public Completable saveAll(List<Report> news) {
        return localNewsRepository.saveAll(reportEntityToReportMapper.reverseMap(news));
    }

    @Override
    public Completable removeAll() {
        return localNewsRepository.removeAll();
    }
}

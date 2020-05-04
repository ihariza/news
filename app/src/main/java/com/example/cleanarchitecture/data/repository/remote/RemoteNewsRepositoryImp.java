package com.example.cleanarchitecture.data.repository.remote;

import com.example.cleanarchitecture.BuildConfig;
import com.example.cleanarchitecture.data.api.NewsApi;
import com.example.cleanarchitecture.data.entity.ReportEntity;
import com.example.cleanarchitecture.data.entity.mapper.ReportDataToReportEntityMapper;

import java.util.List;

import javax.inject.Inject;

import hu.akarnokd.rxjava3.bridge.RxJavaBridge;
import io.reactivex.rxjava3.core.Single;

public class RemoteNewsRepositoryImp implements RemoteNewsRepository {

    private final NewsApi newsApi;
    private final ReportDataToReportEntityMapper reportDataToReportEntityMapper;

    @Inject
    public RemoteNewsRepositoryImp(NewsApi newsApi,
                                   ReportDataToReportEntityMapper reportDataToReportEntityMapper) {
        this.newsApi = newsApi;
        this.reportDataToReportEntityMapper = reportDataToReportEntityMapper;
    }

    @Override
    public Single<List<ReportEntity>> getNews(int pageNumber) {
        return RxJavaBridge.toV3Single(newsApi.getLatestNews(BuildConfig.NEWS_API, "es", pageNumber)
                .map(newsData -> reportDataToReportEntityMapper.map(newsData.getNews())));
    }
}

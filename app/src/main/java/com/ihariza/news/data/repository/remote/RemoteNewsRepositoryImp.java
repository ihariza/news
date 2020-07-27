package com.ihariza.news.data.repository.remote;

import com.ihariza.news.BuildConfig;
import com.ihariza.news.data.api.NewsApi;
import com.ihariza.news.data.entity.ReportEntity;
import com.ihariza.news.data.entity.mapper.ReportDataToReportEntityMapper;

import java.util.List;

import javax.inject.Inject;

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
        return newsApi.getLatestNews(BuildConfig.NEWS_API,
                "es", "", pageNumber)
                .map(newsData -> reportDataToReportEntityMapper.map(newsData.getNews()));
    }
}

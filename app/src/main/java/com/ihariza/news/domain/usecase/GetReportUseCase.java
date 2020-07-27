package com.ihariza.news.domain.usecase;

import com.ihariza.news.domain.model.Report;
import com.ihariza.news.domain.repository.NewsRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetReportUseCase {

    private final NewsRepository newsRepository;

    @Inject
    public GetReportUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Single<Report> getReport(String id) {
        return newsRepository.getReport(id);
    }
}

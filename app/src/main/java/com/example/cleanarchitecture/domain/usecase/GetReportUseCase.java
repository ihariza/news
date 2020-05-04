package com.example.cleanarchitecture.domain.usecase;

import com.example.cleanarchitecture.domain.model.Report;
import com.example.cleanarchitecture.domain.repository.NewsRepository;

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

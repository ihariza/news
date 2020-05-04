package com.example.cleanarchitecture.domain.usecase;

import com.example.cleanarchitecture.domain.model.Report;
import com.example.cleanarchitecture.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class GetNewsUseCase {

    private final NewsRepository newsRepository;

    @Inject
    public GetNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Single<List<Report>> getNews() {
        return newsRepository.getNews();
    }
}

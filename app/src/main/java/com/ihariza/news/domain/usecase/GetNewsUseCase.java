package com.ihariza.news.domain.usecase;

import com.ihariza.news.domain.model.Report;
import com.ihariza.news.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class GetNewsUseCase {

    private final NewsRepository newsRepository;

    @Inject
    public GetNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Single<List<Report>> getNews(int pageNumber) {
        return newsRepository.getNews(pageNumber);
    }
}

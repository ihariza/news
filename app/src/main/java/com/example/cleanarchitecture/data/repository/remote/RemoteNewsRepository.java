package com.example.cleanarchitecture.data.repository.remote;

import com.example.cleanarchitecture.data.entity.ReportEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RemoteNewsRepository {

    Single<List<ReportEntity>> getNews();
}

package com.ihariza.news.data.entity.mapper;

import com.ihariza.news.data.api.datasource.ReportData;
import com.ihariza.news.data.entity.ReportEntity;
import com.ihariza.news.domain.model.mapper.Mapper;

import javax.inject.Inject;

public class ReportDataToReportEntityMapper implements Mapper<ReportData, ReportEntity> {

    @Inject
    public ReportDataToReportEntityMapper() {
        // constructor for injection
    }

    @Override
    public ReportEntity map(ReportData value) {
        final ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(value.getId());
        reportEntity.setTitle(value.getTitle());
        reportEntity.setDescription(value.getDescription());
        reportEntity.setUrl(value.getUrl());
        reportEntity.setAuthor(value.getAuthor());
        reportEntity.setImage(value.getImage());
        reportEntity.setLanguage(value.getLanguage());
        reportEntity.setCategory(value.getCategory());
        reportEntity.setPublished(value.getPublished());
        return reportEntity;
    }

    @Override
    public ReportData reverseMap(ReportEntity value) {
        final ReportData reportData = new ReportData();
        reportData.setId(value.getId());
        reportData.setTitle(value.getTitle());
        reportData.setDescription(value.getDescription());
        reportData.setUrl(value.getUrl());
        reportData.setAuthor(value.getAuthor());
        reportData.setImage(value.getImage());
        reportData.setLanguage(value.getLanguage());
        reportData.setCategory(value.getCategory());
        reportData.setPublished(value.getPublished());
        return reportData;
    }
}

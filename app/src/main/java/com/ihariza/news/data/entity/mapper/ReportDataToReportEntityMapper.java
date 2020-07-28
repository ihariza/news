package com.ihariza.news.data.entity.mapper;

import com.ihariza.news.data.api.datasource.ReportData;
import com.ihariza.news.data.entity.ReportEntity;

import javax.inject.Inject;

public class ReportDataToReportEntityMapper extends Mapper<ReportData, ReportEntity> {

    @Inject
    public ReportDataToReportEntityMapper() {
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
        final ReportData report = new ReportData();
        report.setId(value.getId());
        report.setTitle(value.getTitle());
        report.setDescription(value.getDescription());
        report.setUrl(value.getUrl());
        report.setAuthor(value.getAuthor());
        report.setImage(value.getImage());
        report.setLanguage(value.getLanguage());
        report.setCategory(value.getCategory());
        report.setPublished(value.getPublished());
        return report;
    }
}

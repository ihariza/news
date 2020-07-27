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
        final ReportEntity report = new ReportEntity();
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

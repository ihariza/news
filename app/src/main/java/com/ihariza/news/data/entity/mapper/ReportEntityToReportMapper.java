package com.ihariza.news.data.entity.mapper;

import com.ihariza.news.data.entity.ReportEntity;
import com.ihariza.news.domain.model.Report;
import com.ihariza.news.domain.model.mapper.Mapper;

import javax.inject.Inject;

public class ReportEntityToReportMapper implements Mapper<ReportEntity, Report> {

    @Inject
    public ReportEntityToReportMapper() {
        // constructor for injection
    }

    @Override
    public Report map(ReportEntity value) {
        final Report report = new Report();
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
    public ReportEntity reverseMap(Report value) {
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
}

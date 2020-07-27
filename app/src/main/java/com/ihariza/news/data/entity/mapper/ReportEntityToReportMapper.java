package com.ihariza.news.data.entity.mapper;

import com.ihariza.news.data.entity.ReportEntity;
import com.ihariza.news.domain.model.Report;

import javax.inject.Inject;

public class ReportEntityToReportMapper extends Mapper<ReportEntity, Report> {

    @Inject
    public ReportEntityToReportMapper() {
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
}

package com.ihariza.news.presentation.model.mapper;

import com.ihariza.news.data.entity.mapper.Mapper;
import com.ihariza.news.domain.model.Report;
import com.ihariza.news.presentation.model.ReportDto;

import javax.inject.Inject;

public class ReportToReportDtoMapper extends Mapper<Report, ReportDto> {

    @Inject
    public ReportToReportDtoMapper() {
    }

    @Override
    public ReportDto map(Report value) {
        final ReportDto report = new ReportDto();
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
    public Report reverseMap(ReportDto value) {
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
}
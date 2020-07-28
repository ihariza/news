package com.ihariza.news.presentation.model.mapper;

import com.ihariza.news.data.entity.mapper.Mapper;
import com.ihariza.news.domain.model.Report;
import com.ihariza.news.presentation.model.ReportDto;

import javax.inject.Inject;

public class ReportToReportDtoMapper implements Mapper<Report, ReportDto> {

    @Inject
    public ReportToReportDtoMapper() {
        // constructor for injection
    }

    @Override
    public ReportDto map(Report value) {
        final ReportDto reportDto = new ReportDto();
        reportDto.setId(value.getId());
        reportDto.setTitle(value.getTitle());
        reportDto.setDescription(value.getDescription());
        reportDto.setUrl(value.getUrl());
        reportDto.setAuthor(value.getAuthor());
        reportDto.setImage(value.getImage());
        reportDto.setLanguage(value.getLanguage());
        reportDto.setCategory(value.getCategory());
        reportDto.setPublished(value.getPublished());
        return reportDto;
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
package com.example.cleanarchitecture.presentation.model.mapper;

import com.example.cleanarchitecture.data.entity.mapper.Mapper;
import com.example.cleanarchitecture.domain.model.Report;
import com.example.cleanarchitecture.presentation.model.ReportDto;

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
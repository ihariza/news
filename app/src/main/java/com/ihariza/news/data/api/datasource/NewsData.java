package com.ihariza.news.data.api.datasource;

import java.util.ArrayList;
import java.util.List;

public class NewsData {

    private String status;
    private Integer totalResults;
    private List<ReportData> news = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ReportData> getNews() {
        return news;
    }

    public void setNews(List<ReportData> news) {
        this.news = news;
    }
}

package com.ihariza.news.fake;


import com.ihariza.news.data.entity.ReportEntity;
import com.ihariza.news.domain.model.Report;
import com.ihariza.news.presentation.model.ReportDto;

import java.util.ArrayList;
import java.util.List;

public class FakeNewsLocalAPI {

    public static final String REPORT_ID = "1";

    private static final String JSON_RESPONSE_REPORT = "{\n" +
            "         author: \"straitstimes\",\n" +
            "         category: [\n" +
            "            \"lifestyle\"\n" +
            "         ],\n" +
            "         description: \"LOS ANGELES - Nerdy, dark-skinned Indian girls do not usually get to be the heroine in Hollywood romantic comedies.. Read more at straitstimes.com....\",\n" +
            "         id: \"43ae9d5a-fac0-4f3e-8dbf-93b4781d1a16\",\n" +
            "         image: \"https://www.straitstimes.com/sites/default/files/styles/x_large/public/articles/2020/04/27/ym-nhie-270420.jpg?itok=vaqSUEBr\",\n" +
            "         language: \"en\",\n" +
            "         published: \"2020-04-27 07:11:40 +0000\",\n" +
            "         title: \"Mindy Kaling's Netflix romcom Never Have I Ever puts a Tamil Indian family front and centre\",\n" +
            "         url: \"http://www.straitstimes.com/lifestyle/entertainment/mindy-kalings-netflix-romcom-never-have-i-ever-puts-a-tamil-indian-family\"\n" +
            "      }";

    private static final String JSON_RESPONSE_NEWS = "[{\n" +
            "         author: \"nikkei\",\n" +
            "         category: [\n" +
            "            \"economy\"\n" +
            "         ],\n" +
            "         description: \"BEIJING (Reuters) -- Profits at China's industrial firms fell in March although at a slower pace than in the first two months, with many sectors seeing significant declines, suggesting the economy is ...\",\n" +
            "         id: \"d6f5f18b-c3d9-4803-be0d-01ed3908eada\",\n" +
            "         image: \"None\",\n" +
            "         language: \"en\",\n" +
            "         published: \"2020-04-27 07:12:10 +0000\",\n" +
            "         title: \"China's industrial profits contract in March but at slower pace\",\n" +
            "         url: \"https://asia.nikkei.com/Economy/China-s-industrial-profits-contract-in-March-but-at-slower-pace\"\n" +
            "      },\n" +
            "      {\n" +
            "         author: \"SeekingAlpha.com\",\n" +
            "         category: [\n" +
            "            \"business\",\n" +
            "            \"finance\"\n" +
            "         ],\n" +
            "         description: \"Blank\",\n" +
            "         id: \"ac4303d4-60b6-4453-a4da-0c8abf8aeafa\",\n" +
            "         image: \"None\",\n" +
            "         language: \"en\",\n" +
            "         published: \"2020-04-27 07:11:53 +0000\",\n" +
            "         title: \"Old Republic International's P/B Ratio Is Definitely Out Of Balance\",\n" +
            "         url: \"https://seekingalpha.com/article/4340109-old-republic-internationals-p-b-ratio-is-definitely-out-of-balance?source=feed_sector_financial\"\n" +
            "      }]";

    public static String getJsonResponseReport() {
        return JSON_RESPONSE_REPORT;
    }

    public static String getJsonResponseNews() {
        return JSON_RESPONSE_NEWS;
    }


    public final static String FAKE_REPORT_ID = "003-38dWK2";
    public final static String FAKE_REPORT_TITLE = "Report title";
    public final static String FAKE_REPORT_DESCRIPTION = "Report description";
    private final static String FAKE_REPORT_IMAGE_URL = "http://placeimg.com/640/360/any";
    public final static String FAKE_REPORT_URL = "http://placeimg.com/";
    private final static int FAKE_PAGE = 1;

    public static ReportEntity getFakeReportEntity() {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(FAKE_REPORT_ID);
        reportEntity.setTitle(FAKE_REPORT_TITLE);
        reportEntity.setDescription(FAKE_REPORT_DESCRIPTION);
        reportEntity.setImage(FAKE_REPORT_IMAGE_URL);
        reportEntity.setUrl(FAKE_REPORT_URL);
        reportEntity.setPage(FAKE_PAGE);
        return reportEntity;
    }

    public static List<ReportEntity> getFakeReportEntityList() {
        List<ReportEntity> reportEntityList = new ArrayList<>();
        reportEntityList.add(getFakeReportEntity());
        return reportEntityList;
    }

    public static Report getFakeReport() {
        Report report = new Report();
        report.setId(FAKE_REPORT_ID);
        report.setTitle(FAKE_REPORT_TITLE);
        report.setDescription(FAKE_REPORT_DESCRIPTION);
        report.setImage(FAKE_REPORT_IMAGE_URL);
        report.setUrl(FAKE_REPORT_URL);
        return report;
    }

    public static List<Report> getFakeReportList() {
        List<Report> news = new ArrayList<>();
        news.add(getFakeReport());
        return news;
    }

    public static ReportDto getFakeReportDto() {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(FAKE_REPORT_ID);
        reportDto.setTitle(FAKE_REPORT_TITLE);
        reportDto.setDescription(FAKE_REPORT_DESCRIPTION);
        reportDto.setImage(FAKE_REPORT_IMAGE_URL);
        reportDto.setUrl(FAKE_REPORT_URL);
        return reportDto;
    }

    public static ReportDto getFakeReportWithNoUrlDto() {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(FAKE_REPORT_ID);
        reportDto.setTitle(FAKE_REPORT_TITLE);
        reportDto.setDescription(FAKE_REPORT_DESCRIPTION);
        reportDto.setImage(FAKE_REPORT_IMAGE_URL);
        return reportDto;
    }

    public static List<ReportDto> getFakeReportDtoList() {
        List<ReportDto> reportDtoList = new ArrayList<>();
        reportDtoList.add(getFakeReportDto());
        return reportDtoList;
    }

}

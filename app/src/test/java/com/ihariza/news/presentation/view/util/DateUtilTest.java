package com.ihariza.news.presentation.view.util;

import org.junit.Test;

public class DateUtilTest {

    @Test
    public void givenyyyyMMddTHHmmss0000FormatShouldReturnsddMMyyyyHHmmhFormat() {
        String result = DateUtil.formatTimestampIntoDate("2018-07-11 22:49:24 +0000");
        assert(result.equals("11-07-2018 22:49h"));
    }

    @Test
    public void givenBadFormatShouldReturnsNull() {
        String result = DateUtil.formatTimestampIntoDate("2018-07-11 22:49:24");
        assert(result == null);
    }
}

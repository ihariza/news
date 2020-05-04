package com.example.cleanarchitecture.presentation.view.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {

    /**
     * Transform timestamp into date format
     *
     * @param timestamp yyyy-MM-dd'T'HH:mm:ss+00:00
     * @return date in dd-MM-yyyy
     */
    public static String formatTimestampIntoDate(String timestamp) {
        String newDate = null;
        SimpleDateFormat currentSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss +0000", Locale.getDefault());
        SimpleDateFormat newSdf = new SimpleDateFormat("dd-MM-yyyy",
                Locale.getDefault());
        try {
            long currentTime = currentSdf.parse(timestamp).getTime();
            newDate = newSdf.format(currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}

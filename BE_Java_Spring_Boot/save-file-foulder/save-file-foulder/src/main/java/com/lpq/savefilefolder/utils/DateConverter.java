package com.lpq.savefilefolder.utils;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {
    public static Timestamp convertMsToTimestamp(String milisecond) {
        if (StringUtils.hasText(milisecond)) {
            Date date = new Date(Long.parseLong(milisecond));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);

            return Timestamp.valueOf(formatted);
        } else {
            return null;
        }
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDate, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateFormat);
    }
}

package org.vector.littlejourney.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends Date {

    public static final String DATE_FORMAT = "HH:mm";

    public static String toSimpleFormat(Date duration, String pattern) {

        DateFormat dateFormat = new SimpleDateFormat(pattern);

        return dateFormat.format(duration);
    }

    public static int compare(Date time, Date anotherTime) {

        String thisTime = toSimpleFormat(time, DATE_FORMAT);

        String thatTime = toSimpleFormat(anotherTime, DATE_FORMAT);

        return thisTime.compareTo(thatTime);
    }
}

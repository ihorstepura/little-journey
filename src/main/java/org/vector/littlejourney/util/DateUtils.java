package org.vector.littlejourney.util;

import org.vector.littlejourney.constant.DateConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String toSimpleFormat(Date duration, String pattern) {

        DateFormat dateFormat = new SimpleDateFormat(pattern);

        return dateFormat.format(duration);
    }

    public static int compare(Date time, Date anotherTime) {

        String thisTime = toSimpleFormat(time, DateConstants.DATE_FORMAT_HH_mm);

        String thatTime = toSimpleFormat(anotherTime, DateConstants.DATE_FORMAT_HH_mm);

        return thisTime.compareTo(thatTime);
    }
}

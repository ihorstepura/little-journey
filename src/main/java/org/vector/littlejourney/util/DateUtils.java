package org.vector.littlejourney.util;

import org.vector.littlejourney.util.constant.DateConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String toSimpleFormat(Date duration, String pattern) {

        DateFormat dateFormat = new SimpleDateFormat(pattern);

        return dateFormat.format(duration);
    }

    public static int compare(Date time, Date anotherTime) {

        String thisTime = toSimpleFormat(time, DateConstant.DATE_FORMAT_dd_HH_mm);

        String thatTime = toSimpleFormat(anotherTime, DateConstant.DATE_FORMAT_dd_HH_mm);

        return thisTime.compareTo(thatTime);
    }

    public static Date toDateFormat(String time) {

        String[] result = time.split(":");

        Date date = new Date();

            //using deprecated methods of Date class is needed because of using Date class in custom DateUtils
            date.setDate(Integer.parseInt(result[0]));
            date.setHours(Integer.parseInt(result[1]));
            date.setMinutes(Integer.parseInt(result[2]));

        return date;
    }
}

package org.vector.littlejourney.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends Date {

    public static String toSimpleFormat(Date duration, String pattern) {

        DateFormat dateFormat = new SimpleDateFormat(pattern);

        return dateFormat.format(duration);
    }

    @Override
    public int compareTo(Date anotherDate) {

        return super.compareTo(anotherDate);
    }
}

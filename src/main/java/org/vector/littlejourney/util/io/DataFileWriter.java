package org.vector.littlejourney.util.io;

import org.vector.littlejourney.constant.DateConstant;
import org.vector.littlejourney.constant.FormatConstant;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;

import java.io.*;
import java.util.List;

public class DataFileWriter {

    private static String departure;
    private static String arrival;
    private static String cost;
    private static String duration;

    public static void writeXLSX(String fileName, List<Trip> trips) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            for (Trip value : trips) {

                departure = value.getRoute().getDeparture().toString();
                arrival = value.getRoute().getArrival().toString();
                cost = String.valueOf(String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, value.getCost()));
                duration = DateUtils.toSimpleFormat(value.getDuration(), DateConstant.DATE_FORMAT_HH_mm);

                writer.write(departure);
                writer.write("\t");

                writer.write(arrival);
                writer.write("\t");


                writer.write(cost);
                writer.write("\t");

                writer.write(duration);
                writer.write("\t");

                writer.newLine();
            }
            writer.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void writeTXT_DOCX(String fileName, List<Trip> trips) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Trip value : trips) {

                departure = value.getRoute().getDeparture().toString();
                arrival = value.getRoute().getArrival().toString();
                cost = String.valueOf(String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, value.getCost()));
                duration = DateUtils.toSimpleFormat(value.getDuration(), DateConstant.DATE_FORMAT_HH_mm);

                writer.write(departure + "-" + arrival + "-" + cost + "-" + duration);
                writer.newLine();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

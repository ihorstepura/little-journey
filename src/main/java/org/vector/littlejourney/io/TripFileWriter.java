package org.vector.littlejourney.io;

import org.vector.littlejourney.util.constant.DateConstant;
import org.vector.littlejourney.util.constant.FormatConstant;
import org.vector.littlejourney.util.constant.TripConstant;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;

import java.io.*;
import java.util.List;

public class TripFileWriter {

    private static String departure;
    private static String arrival;
    private static String cost;
    private static String duration;

    public static void writeXLSX(String fileName, List<Trip> trips) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            writer.write(TripConstant.DEPARTURE);
            writer.write(FormatConstant.TAB_SYMBOL);

            writer.write(TripConstant.ARRIVAL);
            writer.write(FormatConstant.TAB_SYMBOL);

            writer.write(TripConstant.COST);
            writer.write(FormatConstant.TAB_SYMBOL);

            writer.write(TripConstant.DURATION);
            writer.newLine();

            for (Trip value : trips) {

                departure = value.getRoute().getDeparture().toString();
                arrival = value.getRoute().getArrival().toString();
                cost = String.valueOf(String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, value.getCost()));
                duration = DateUtils.toSimpleFormat(value.getDuration(), DateConstant.DATE_FORMAT_HH_mm);

                writer.write(departure);
                writer.write(FormatConstant.TAB_SYMBOL);

                writer.write(arrival);
                writer.write(FormatConstant.TAB_SYMBOL);

                writer.write(cost);
                writer.write(FormatConstant.TAB_SYMBOL);

                writer.write(duration);

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

                departure = TripConstant.DEPARTURE + ":" + value.getRoute().getDeparture().toString();
                arrival = TripConstant.ARRIVAL + ":" + value.getRoute().getArrival().toString();
                cost = TripConstant.COST + ":" + String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, value.getCost());
                duration = TripConstant.DURATION + ":" + DateUtils.toSimpleFormat(value.getDuration(), DateConstant.DATE_FORMAT_HH_mm);

                writer.write(departure + "," + arrival + "," + cost + "," + duration);

                writer.newLine();
                writer.newLine();

            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

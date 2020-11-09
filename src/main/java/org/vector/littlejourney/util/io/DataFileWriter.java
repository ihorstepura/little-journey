package org.vector.littlejourney.util.io;

import org.vector.littlejourney.constant.DateConstant;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;

import java.io.*;
import java.util.List;

public class DataFileWriter {

    public void writeTXT(String fileName, List<Trip> trips) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            for (Trip value : trips) {

                String route = value.getRoute().toString();
                String cost = String.valueOf(String.format("%.2f", value.getCost()));
                String duration = DateUtils.toSimpleFormat(value.getDuration(), DateConstant.DATE_FORMAT_HH_mm);

                writer.write(route + "-" + cost + "-" + duration);

                writer.append('\n');
            }
            writer.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void writeXLSX(File file, List<Trip> trips) {

    }

    public void writeDOCX(File file, List<Trip> trips) {

    }
}

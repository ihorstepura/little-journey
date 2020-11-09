package org.vector.littlejourney.util.io;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.InputTripsConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileReader {

    public static List<Trip> read(String fileName, String regex) {

        List<String> trips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {

                String trip = reader.readLine();

                trips.add(trip);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return InputTripsConverter.convert(trips, regex);
    }
}

package org.vector.littlejourney.io;

import org.vector.littlejourney.entity.Trip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TripFileReader {

    public static List<Trip> read(String fileName) {

        List<String> trips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {

                String trip = reader.readLine();

                trips.add(trip);

            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println(trips);
        return null;
    }
}

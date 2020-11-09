package org.vector.littlejourney.util.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileReader {

    public List<String> readTXT(String fileName) {

        List<String> trips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {

            while (reader.ready()) {

                String trip = reader.readLine();

                trips.add(trip);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return trips;
    }
}

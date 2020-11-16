package org.vector.littlejourney.util.file;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.service.TripHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocumentHandler extends FileHandler {

    @Override
    public List<List<String>> process(File file) {

        List<List<String>> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            List<String> header = new ArrayList<>();

            List<String> otherRows = new ArrayList<>();

            while (reader.ready()) {

                String trip = reader.readLine();

                String[] points = trip.split(",");

                if (header.isEmpty()) {

                    for (String point : points) {
                        String head = point.substring(0, point.indexOf(":"));
                        header.add(head);
                    }
                    Collections.addAll(rows, header);
                }

                for (String point : points) {
                    String row = point.substring(point.indexOf(":") + 1);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return rows;
    }

    public static <E> void writeDocument(String fileName, List<E> elements) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (E element : elements) {

                writer.write(TripHelper.prepareDocument((Trip) element));

                writer.newLine();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}

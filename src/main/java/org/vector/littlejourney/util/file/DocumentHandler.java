package org.vector.littlejourney.util.file;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.service.TripHelper;
import org.vector.littlejourney.util.constant.StringConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocumentHandler implements FileHandler {

    @Override
    public List<List<String>> process(File file) {

        List<List<String>> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            List<String> header = new ArrayList<>();

            while (reader.ready()) {

                String row = reader.readLine();

                String[] parts = row.split(StringConstant.COMMA);

                if (header.isEmpty()) {

                    for (String point : parts) {

                        String head = point.substring(0, point.indexOf(StringConstant.COLON));

                        header.add(head);
                    }
                    rows.add(header);
                }
                List<String> otherRows = new ArrayList<>();

                for (String point : parts) {

                    String body = point.substring(point.indexOf(StringConstant.COLON) + 1);

                    Collections.addAll(otherRows, body);
                }
                rows.add(otherRows);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public <E> void write(String fileName, List<E> elements) {

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

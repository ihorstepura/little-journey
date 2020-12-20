package org.vector.littlejourney.util.file;

import org.vector.littlejourney.dal.constant.FormatConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpreadsheetHandler implements FileHandler {

    @Override
    public List<List<String>> process(File file) {

        List<List<String>> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while (reader.ready()) {

                String line = reader.readLine();

                List<String> row = new ArrayList<>();

                Collections.addAll(row, line.split(FormatConstant.TAB_SYMBOL));

                rows.add(row);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public <E> void write(String fileName, List<E> elements) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            //header writing
            writer.write(String.valueOf(elements));
            writer.newLine();

            //body writing
            for (E element : elements) {

                writer.write((String) element);
                writer.newLine();
            }
            writer.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

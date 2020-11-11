package org.vector.littlejourney.util.file;

import org.vector.littlejourney.util.constant.FormatConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XlsxFileHandler extends FileHandler {

    @Override
    public List<List<String>> process(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<List<String>> rows = new ArrayList<>();

        while (reader.ready()) {

            String trip = reader.readLine();

            List<String> row = new ArrayList<>();

            Collections.addAll(row, trip.split(FormatConstant.TAB_SYMBOL));

            rows.add(row);
        }

        return rows;
    }
}

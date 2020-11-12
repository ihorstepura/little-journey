package org.vector.littlejourney.util.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocxFileHandler extends FileHandler {

    @Override
    public List<List<String>> process(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<List<String>> rows = new ArrayList<>();

        while (reader.ready()) {

            String trip = reader.readLine();

            List<String> row = new ArrayList<>();

            List<String> header = new ArrayList<>();

            rows.add(row);
        }
        return rows;
    }
}

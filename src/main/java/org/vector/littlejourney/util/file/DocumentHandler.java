package org.vector.littlejourney.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DocumentHandler extends FileHandler {

    @Override
    public List<List<String>> process(File file) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        List<List<String>> rows = new ArrayList<>();

        return null;
    }
}

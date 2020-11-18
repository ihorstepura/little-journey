package org.vector.littlejourney.util.file;

import org.vector.littlejourney.util.file.exception.FileException;

import java.io.File;
import java.util.List;

public interface FileHandler {

    List<List<String>> process(File file) throws FileException;

    <E> void write(String fileName, List<E> elements);
}

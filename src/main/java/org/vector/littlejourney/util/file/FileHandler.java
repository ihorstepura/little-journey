package org.vector.littlejourney.util.file;

import org.vector.littlejourney.util.file.exception.FileException;

import java.io.File;
import java.util.List;

public abstract class FileHandler {

    public abstract List<List<String>> process(File file) throws FileException;
}

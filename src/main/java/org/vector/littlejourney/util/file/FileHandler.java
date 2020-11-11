package org.vector.littlejourney.util.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class FileHandler {

    public abstract List<List<String>> process(File file) throws IOException;
}

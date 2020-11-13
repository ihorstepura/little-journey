package org.vector.littlejourney.exception.file;

//TODO:: unite InvalidFileFormatException.class and UnsupportedFileExtensionException.class
//into FileException.class and provide related error messages
public class InvalidFileFormatException extends RuntimeException {

    public InvalidFileFormatException(String message) {

        super(message);
    }
}

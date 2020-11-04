package org.vector.littlejourney.exception;

public class InvalidStationNameException extends RuntimeException {

    public InvalidStationNameException(Reason reason) {

        super(reason.getReason());
    }

}

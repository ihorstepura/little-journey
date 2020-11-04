package org.vector.littlejourney.exception;

public class InvalidObjectBuildException extends RuntimeException {

    public InvalidObjectBuildException(Reason reason) {

        super(reason.getReason());
    }
}

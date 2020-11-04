package org.vector.littlejourney.exception;

public class InvalidCostValueException extends RuntimeException {

    public InvalidCostValueException(Reason reason) {

        super(reason.getReason());
    }
}

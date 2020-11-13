package org.vector.littlejourney.exception.duration;

import org.vector.littlejourney.util.constant.duration.DurationWarning;

public class InvalidDurationException extends RuntimeException {

    public InvalidDurationException(String message) {

        //TODO:: modify message

        // super(DurationWarning.INVALID_DURATION + " : " + message);
        super(DurationWarning.INVALID_DURATION + " : " + message);
    }
}

package org.vector.littlejourney.entity.exception;

import org.vector.littlejourney.util.constant.duration.DurationWarning;

public class InvalidDurationException extends Exception {

    public InvalidDurationException(String message) {

        super(DurationWarning.INVALID_DURATION + " : " + message);
    }
}

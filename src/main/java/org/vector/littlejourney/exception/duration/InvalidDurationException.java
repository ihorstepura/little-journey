package org.vector.littlejourney.exception.duration;

import org.vector.littlejourney.constant.duration.DurationWarning;

public class InvalidDurationException extends RuntimeException {

    public InvalidDurationException(String message) {

        super(DurationWarning.INVALID_DURATION + message);
    }
}

package org.vector.littlejourney.exception.entity;

import org.vector.littlejourney.util.constant.StringConstant;
import org.vector.littlejourney.util.constant.duration.DurationWarning;

public class InvalidDurationException extends Exception {

    public InvalidDurationException(String message) {

        super(DurationWarning.INVALID_DURATION + StringConstant.COLON + message);
    }
}

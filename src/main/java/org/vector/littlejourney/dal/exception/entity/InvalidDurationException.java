package org.vector.littlejourney.dal.exception.entity;

import org.vector.littlejourney.dal.constant.StringConstant;
import org.vector.littlejourney.dal.constant.duration.DurationWarning;

public class InvalidDurationException extends Exception {

    public InvalidDurationException(String message) {

        super(DurationWarning.INVALID_DURATION + StringConstant.COLON + message);
    }
}

package org.vector.littlejourney.exception;

import org.vector.littlejourney.constant.CostWarning;

public class InvalidCostException extends RuntimeException {

    public InvalidCostException(String message) {

        super(CostWarning.INVALID_COST + message);
    }
}

package org.vector.littlejourney.exception;

import org.vector.littlejourney.util.constant.CostWarning;

public class InvalidCostException extends RuntimeException {

    public InvalidCostException(String message) {

        super(CostWarning.INVALID_COST + message);
    }
}

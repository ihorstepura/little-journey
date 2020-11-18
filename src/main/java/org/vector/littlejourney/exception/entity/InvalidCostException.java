package org.vector.littlejourney.exception.entity;

import org.vector.littlejourney.util.constant.CostWarning;
import org.vector.littlejourney.util.constant.StringConstant;

public class InvalidCostException extends RuntimeException {

    public InvalidCostException(String message) {

        super(CostWarning.INVALID_COST + StringConstant.COLON + message);
    }
}

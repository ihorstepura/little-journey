package org.vector.littlejourney.dal.exception.entity;

import org.vector.littlejourney.dal.constant.CostWarning;
import org.vector.littlejourney.dal.constant.StringConstant;

public class InvalidCostException extends RuntimeException {

    public InvalidCostException(String message) {

        super(CostWarning.INVALID_COST + StringConstant.COLON + message);
    }
}

package org.vector.littlejourney.dal.exception.entity;

import org.vector.littlejourney.dal.constant.StringConstant;
import org.vector.littlejourney.dal.constant.route.RouteWarning;

public class InvalidRouteException extends RuntimeException {

    public InvalidRouteException(String message) {

        super(RouteWarning.INVALID_ROUTE + StringConstant.COLON + message);
    }
}

package org.vector.littlejourney.util.exception.entity;

import org.vector.littlejourney.util.constant.StringConstant;
import org.vector.littlejourney.util.constant.route.RouteWarning;

public class InvalidRouteException extends RuntimeException {

    public InvalidRouteException(String message) {

        super(RouteWarning.INVALID_ROUTE + StringConstant.COLON + message);
    }
}

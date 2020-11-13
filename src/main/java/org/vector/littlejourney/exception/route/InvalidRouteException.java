package org.vector.littlejourney.exception.route;

import org.vector.littlejourney.util.constant.route.RouteWarning;

public class InvalidRouteException extends RuntimeException {

    public InvalidRouteException(String message) {

        //TODO:: see InvalidDurationException.class constructor
        super(RouteWarning.INVALID_ROUTE + message);
    }
}

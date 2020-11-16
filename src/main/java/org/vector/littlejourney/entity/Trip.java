package org.vector.littlejourney.entity;

import org.vector.littlejourney.util.constant.CostWarning;
import org.vector.littlejourney.util.constant.duration.DurationWarning;
import org.vector.littlejourney.util.constant.route.RouteWarning;
import org.vector.littlejourney.entity.exception.InvalidDurationException;
import org.vector.littlejourney.entity.exception.InvalidRouteException;
import org.vector.littlejourney.entity.exception.InvalidCostException;

import java.util.Date;

public class Trip {

    private Route route;

    private double cost;

    private Date duration;

    public Trip(Route route, double cost, Date duration) {

        if (route == null || duration == null) {

            throw new InvalidRouteException(RouteWarning.ROUTE_NOT_DEFINED);
        }
        if (cost < 0) {

            throw new InvalidCostException(CostWarning.LESS_THAN_ZERO_COST);
        }
        this.route = route;
        this.cost = cost;
        this.duration = duration;
    }

    public Route getRoute() {

        return route;
    }

    public void setRoute(Route route) {

        if (route == null) {

            throw new InvalidRouteException(RouteWarning.ROUTE_NOT_DEFINED);
        }
        this.route = route;
    }

    public double getCost() {

        return cost;
    }

    public void setCost(double cost) {

        if (cost < 0) {

            throw new InvalidCostException(CostWarning.LESS_THAN_ZERO_COST);
        }
        this.cost = cost;
    }

    public Date getDuration() {

        return duration;
    }

    public void setDuration(Date duration) {

        if (duration == null) {

            throw new InvalidDurationException(DurationWarning.DURATION_NOT_DEFINED);
        }
        this.duration = duration;
    }
}

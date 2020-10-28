package org.vector;

import java.util.Date;

public class Ticket {

    private Route route;

    private int cost;

    private Date routeDuration;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getRouteDuration() {
        return routeDuration;
    }

    public void setRouteDuration(Date routeDuration) {
        this.routeDuration = routeDuration;
    }
}

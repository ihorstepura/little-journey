package org.vector.littlejourney.entity;

import org.vector.littlejourney.utils.DateUtils;

import java.util.Date;

public class Trip {

    private Route route;

    private double cost;

    private Date duration;

    public Trip(Route route, double cost, Date duration) {

        try {

            this.route = route;

            this.cost = cost;

            this.duration = duration;

        } catch (NullPointerException e) {

            e.printStackTrace();
        }
    }

    public Route getRoute() {

        return route;
    }

    public void setRoute(Route route) {

        try {
            this.route = route;

        } catch (NullPointerException e) {

            e.printStackTrace();
        }
    }

    public double getCost() {

        return cost;
    }

    public void setCost(double cost) {

        if (cost <= 0) {

            throw new IllegalArgumentException();
        } else {

            this.cost = cost;
        }


    }

    public Date getDuration() {

        return duration;
    }

    public void setDuration(Date duration) {

        try {
            this.duration = duration;

        } catch (NullPointerException e) {

            e.printStackTrace();
        }
    }

    @Override
    public String toString() {

        return "Станция отправления - " + route.getDeparture().toString().toUpperCase() + "; " +

                "станция прибытия - " + route.getArrival().toString().toUpperCase() + "; " +

                "цена: " + String.format("%.2f", cost) + "; " + "длительность маршрута: " +

                DateUtils.toSimpleFormat(duration, "hh:mm");
    }
}

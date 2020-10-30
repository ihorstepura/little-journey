package org.vector.littlejourney;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    private Route route;

    private double cost;

    private Date duration;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Станция отправления - " + route.getDeparture().toString().toUpperCase() + "; " +
               "станция прибытия - " +  route.getArrival().toString().toUpperCase() + "; " +
                "цена: " + String.format("%.2f", cost) + "; " + "длительность маршрута: " + dateFormatter(duration);
    }

    private String dateFormatter(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");

        return dateFormat.format(date);
    }
}

package org.vector.littlejourney;

public class Route {

    private Station departure;

    private Station arrival;

    public Station getDeparture() {
        return departure;
    }

    public void setDeparture(Station departure) {
        this.departure = departure;
    }

    public Station getArrival() {
        return arrival;
    }

    public void setArrival(Station arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return departure.toString().toUpperCase() + "-" + arrival.toString().toUpperCase();
    }
}

package org.vector.littlejourney.entity;

import org.vector.littlejourney.util.constant.WarningConstant;
import org.vector.littlejourney.util.exception.entity.InvalidStationException;

public class Route {

    private int id;

    private Station departure;

    private Station arrival;

    public Route() {
    }

    public Route(Station departure, Station arrival) {

        if (departure == null || arrival == null) {

            throw new InvalidStationException(WarningConstant.NULL_REFERENCE);
        }
        this.departure = departure;
        this.arrival = arrival;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Station getDeparture() {

        return departure;
    }

    public void setDeparture(Station departure) {

        if (departure == null) {

            throw new InvalidStationException(WarningConstant.NULL_REFERENCE);
        }
        this.departure = departure;
    }

    public Station getArrival() {

        return arrival;
    }

    public void setArrival(Station arrival) {

        if (arrival == null) {

            throw new InvalidStationException(WarningConstant.NULL_REFERENCE);
        }
        this.arrival = arrival;
    }

    @Override
    public String toString() {

        return "Route{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}

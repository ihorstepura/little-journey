package org.vector.littlejourney.entity;

import org.vector.littlejourney.constant.station.StationWarning;
import org.vector.littlejourney.exception.station.InvalidStationException;

public class Route {

    private Station departure;

    private Station arrival;

    public Route(Station departure, Station arrival) {

        if (departure == null || arrival == null) {

            throw new InvalidStationException(StationWarning.STATION_NOT_DEFINED);
        }

        this.departure = departure;
        this.arrival = arrival;
    }

    public Station getDeparture() {

        return departure;
    }

    public void setDeparture(Station departure) {

        if (departure == null) {

            throw new InvalidStationException(StationWarning.STATION_NOT_DEFINED);
        }

        this.departure = departure;
    }

    public Station getArrival() {

        return arrival;
    }

    public void setArrival(Station arrival) {

        if (arrival == null) {

            throw new InvalidStationException(StationWarning.STATION_NOT_DEFINED);
        }

        this.arrival = arrival;
    }

    @Override
    public String toString() {

        return departure.toString() + "-" + arrival.toString();
    }
}

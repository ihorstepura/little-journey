package org.vector.littlejourney.entity;

import org.vector.littlejourney.util.constant.WarningConstant;
import org.vector.littlejourney.exception.entity.InvalidStationException;

public class Route {

    private Station departure;

    private Station arrival;

    public Route(Station departure, Station arrival) {

        if (departure == null || arrival == null) {

            throw new InvalidStationException(WarningConstant.NULL_REFERENCE);
        }
        this.departure = departure;
        this.arrival = arrival;
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
}

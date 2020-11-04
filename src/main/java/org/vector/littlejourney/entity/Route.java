package org.vector.littlejourney.entity;

import org.vector.littlejourney.exception.InvalidObjectBuildException;
import org.vector.littlejourney.exception.Reason;

public class Route {

    private Station departure;

    private Station arrival;

    public Route(Station departure, Station arrival) {

        if (departure == null || arrival == null) {

            throw new InvalidObjectBuildException(Reason.NULL_REFERENCE);

        } else {

            this.departure = departure;

            this.arrival = arrival;
        }
    }

    public Station getDeparture() {

        return departure;
    }

    public void setDeparture(Station departure) {

        if (departure == null) {

            throw new InvalidObjectBuildException(Reason.NULL_REFERENCE);

        } else {

            this.departure = departure;

        }
    }

    public Station getArrival() {

        return arrival;
    }

    public void setArrival(Station arrival) {

        if (arrival == null) {

            throw new InvalidObjectBuildException(Reason.NULL_REFERENCE);

        } else {

            this.arrival = arrival;
        }
    }

    @Override
    public String toString() {

        return departure.toString().toUpperCase() + "-" + arrival.toString().toUpperCase();
    }
}

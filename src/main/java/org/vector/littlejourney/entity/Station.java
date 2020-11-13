package org.vector.littlejourney.entity;

import org.vector.littlejourney.util.constant.station.StationWarning;
import org.vector.littlejourney.exception.station.InvalidStationException;

import java.util.Objects;

public class Station {

    private final String name;

    public Station(String name) {

        if (name == null) {

            //TODO:: change warning message and add check for empty name
            throw new InvalidStationException(StationWarning.STATION_NOT_DEFINED);
        }

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    //TODO:: remove
    @Override
    public String toString() {

        return name;
    }
}

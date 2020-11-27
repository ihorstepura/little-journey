package org.vector.littlejourney.entity;

import org.vector.littlejourney.util.constant.WarningConstant;
import org.vector.littlejourney.util.constant.station.StationWarning;
import org.vector.littlejourney.util.exception.entity.InvalidStationException;

import java.util.Objects;

public class Station {

    private int id;

    private String name;

    public Station(int id) {

        this.id = id;
    }

    public Station(String name) {

        if (name == null) {

            throw new InvalidStationException(WarningConstant.NULL_REFERENCE);
        }
        if (name.isEmpty()) {

            throw new InvalidStationException(StationWarning.STATION_EMPTY_NAME);
        }
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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

    @Override
    public String toString() {

        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

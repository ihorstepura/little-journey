package org.vector.littlejourney.entity;

import java.util.Objects;

public class Station {

    private String name;

    public Station(String name) {

        try {

            this.name = name;

        } catch (NullPointerException e) {

            e.printStackTrace();
        }
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

    @Override
    public String toString() {

        return name;
    }
}
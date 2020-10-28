package org.vector;

import org.vector.mock.StationFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Station> stations = new StationFactory().generateStations(10);
        System.out.println(stations);
        System.out.println(stations.size());

        Station station = stations.get(0);
        System.out.println(station);

        Route route = new Route();
        System.out.println(route.getDeparture());
    }
}

package org.vector.mock;

import org.vector.Route;
import org.vector.Station;

import java.util.ArrayList;
import java.util.List;

public class RouteFactory {

    private final StationFactory stationFactory = new StationFactory();

    public List<Route> generateRouts(int number) {
        return generateRout(number);
    }

    private List<Route> generateRout(int number) {

        List<Station> departments = stationFactory.generateStations(number);

        List<Station> arrival = stationFactory.generateStations(number);

        List<Route> routes = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {

            routes.add(new Route(departments.get(i), arrival.get(i)));

        }

        return routes;
    }
}

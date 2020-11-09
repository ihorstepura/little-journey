package org.vector.littlejourney.mock;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;

import java.util.ArrayList;
import java.util.List;

public class RouteFactory {

    private static RouteFactory instance;

    private final StationFactory stationFactory = StationFactory.getInstance();

    private RouteFactory() {

    }

    public static RouteFactory getInstance() {

        if (instance == null) {

            instance = new RouteFactory();
        }

        return instance;
    }

    public List<Route> generateRouts(int count) {

        List<Station> departments = stationFactory.generateStations(count);

        List<Station> arrival = stationFactory.generateStations(count);

        List<Route> routes = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {

            routes.add(new Route(departments.get(i), arrival.get(i)));
        }

        return routes;
    }
}

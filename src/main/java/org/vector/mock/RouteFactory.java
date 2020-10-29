package org.vector.mock;

import org.vector.Route;
import org.vector.Station;

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

    public List<Route> generateRouts(int number) {

        return generateRout(number);
    }

    private List<Route> generateRout(int number) {

        List<Station> departments = stationFactory.generateStations(number);

        List<Station> arrival = stationFactory.generateStations(number);

        List<Route> routes = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {

            routes.add(new Route());
        }

        for (int i = 0; i < number; i++) {

            routes.get(i).setDeparture(departments.get(i));
            routes.get(i).setArrival(arrival.get(i));
        }

        return routes;
    }
}

package org.vector.littlejourney.mock;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.RandomDataGenerator;

import java.util.*;

public class TripFactory {

    private static TripFactory instance;

    private final RouteFactory routeFactory = RouteFactory.getInstance();

    private TripFactory() {

    }

    public static TripFactory getInstance() {

        if (instance == null) {

            instance = new TripFactory();
        }
        return instance;
    }

    public List<Trip> generateTickets(int count) {

        List<Route> routes = routeFactory.generateRouts(count);

        List<Trip> trips = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {

            trips.add(new Trip(routes.get(i),
                    RandomDataGenerator.randomDouble(20, 1000),
                    RandomDataGenerator.randomDate()));
        }
        return trips;
    }
}
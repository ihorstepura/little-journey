package org.vector.littlejourney.mock;

import org.vector.littlejourney.database.entity.Route;
import org.vector.littlejourney.database.entity.Trip;
import org.vector.littlejourney.util.RandomDataGenerationUtils;

import java.util.*;

public class TripFactory {

    private static TripFactory trip;

    private final RouteFactory routeFactory = RouteFactory.getRoute();

    private TripFactory() {

    }

    public static TripFactory getTrip() {

        if (trip == null) {

            trip = new TripFactory();
        }
        return trip;
    }

    public List<Trip> generateTrips(int count) {

        List<Route> routes = routeFactory.generateRouts(count);

        List<Trip> trips = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {

            int priceFrom = 20;
            int priceTo = 1000;

            trips.add(new Trip(routes.get(i),
                    RandomDataGenerationUtils.getRandomDouble(priceFrom, priceTo),
                    RandomDataGenerationUtils.getRandomDate()));
        }
        return trips;
    }
}

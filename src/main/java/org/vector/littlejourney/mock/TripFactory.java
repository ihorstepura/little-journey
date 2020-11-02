package org.vector.littlejourney.mock;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.utils.RandomDataGenerator;

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

    public List<Trip> generateTickets(int number) {

        return generateTicket(number);
    }

    private List<Trip> generateTicket(int number) {

        List<Route> routes = routeFactory.generateRouts(number);

        List<Trip> trips = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {

            trips.add(new Trip(routes.get(i),
                    RandomDataGenerator.randomDouble(20, 1000),
                    RandomDataGenerator.randomDate()));
        }

        return trips;
    }
}
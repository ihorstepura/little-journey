package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.DatabaseRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Trip;

public class TripService {

    private TripService() {
    }

    public static Trip generateTrip(Trip trip) {

        int tripId = trip.getId();

        int routeId = DatabaseRepository.getRouteId(tripId);

        Route route = new Route(routeId);

        trip = DatabaseRepository.getTripById(tripId);

        trip.setRoute(RouteService.generateRoute(route));

        return trip;
    }
}

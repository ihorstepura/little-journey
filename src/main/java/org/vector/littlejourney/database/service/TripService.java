package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.RouteRepository;
import org.vector.littlejourney.database.repository.TripRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Trip;

public class TripService {

    private TripService() {
    }

    public static Trip generateTrip(Trip trip) {

        int tripId = trip.getId();

        int routeId = RouteRepository.getRouteIdByTripId(tripId);

        Route route = new Route(routeId);

        trip = TripRepository.getTripById(tripId);

        trip.setRoute(RouteService.generateRoute(route));

        return trip;
    }
}

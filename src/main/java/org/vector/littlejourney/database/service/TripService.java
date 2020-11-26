package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.RouteRepository;
import org.vector.littlejourney.database.repository.TripRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Trip;

public class TripService {

    private static final RouteService routeService = new RouteService();

    private static final RouteRepository routeRepository = new RouteRepository();

    private static final TripRepository tripRepository = new TripRepository();

    public static Trip generateTrip(Trip trip) {

        int tripId = trip.getId();

        int routeId = routeRepository.getRouteId(tripId);

        Route route = new Route(routeId);

        tripRepository.getTripById(trip);

        trip.setRoute(routeService.generateRoute(route));

        return trip;
    }
}

package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.DatabaseRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;

public class RouteService {

    private RouteService() {
    }

    public static Route generateRoute(Route route) {

        int routeId = route.getId();

        int departureStationId = DatabaseRepository.getDepartureStationId(routeId);
        int arrivalStationId = DatabaseRepository.getArrivalStationId(routeId);

        Station departure = new Station(departureStationId);
        Station arrival = new Station(arrivalStationId);

        route.setDeparture(StationService.generateStation(departure));
        route.setArrival(StationService.generateStation(arrival));

        return route;
    }
}

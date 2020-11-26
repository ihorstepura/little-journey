package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.StationRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;

public class RouteService {

    private static final StationRepository stationRepository = new StationRepository();

    public Route generateRoute(Route route) {

        int routeId = route.getId();

        int departureStationId = stationRepository.getDepartureStationId(routeId);
        int arrivalStationId = stationRepository.getArrivalStationId(routeId);

        Station departure = new Station(departureStationId);
        Station arrival = new Station(arrivalStationId);

        route.setDeparture(StationService.generateStation(departure));
        route.setArrival(StationService.generateStation(arrival));

        return route;
    }
}

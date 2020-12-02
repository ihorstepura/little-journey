package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.StationRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;

public class RouteService {

    private RouteService() {
    }

    public static Route getRouteAttributes(Route route) {

        int routeId = route.getId();

        int departureStationId = StationRepository.getDepartureStationIdByRouteId(routeId);
        int arrivalStationId = StationRepository.getArrivalStationIdByRouteId(routeId);

        Station departure = new Station(departureStationId);
        Station arrival = new Station(arrivalStationId);

        route.setDeparture(StationService.getStationAttributes(departure));
        route.setArrival(StationService.getStationAttributes(arrival));

        return route;
    }
}

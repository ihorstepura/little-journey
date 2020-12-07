package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.RouteRepository;
import org.vector.littlejourney.database.entity.Route;

public class RouteService {

    private static final RouteRepository routeRepository = RouteRepository.getRouteRepository();

    private RouteService() {
    }

    public static Route getRouteById(int routeId) {

        return routeRepository.getById(routeId);
    }
}

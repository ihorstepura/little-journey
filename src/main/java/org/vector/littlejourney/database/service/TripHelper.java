package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.StationRepository;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TripHelper {

    private TripHelper() {
    }

    public static void prepareTrip(ResultSet resultSet, List<Trip> trips) throws SQLException {

        while (resultSet.next()) {

            Trip trip = new Trip();
            Route route = new Route();
            Station departure = new Station();
            Station arrival = new Station();

            int tripId = resultSet.getInt(1);
            double tripCost = resultSet.getDouble(2);
            String tripDuration = resultSet.getString(3);
            int routeId = resultSet.getInt(4);

            route.setId(routeId);

            int departureStationId = StationRepository.getDepartureStationIdByRouteId(routeId);
            departure.setId(departureStationId);
            departure.setName(StationRepository.getStationById(departureStationId).getName());

            int arrivalStationId = StationRepository.getArrivalStationIdByRouteId(routeId);
            arrival.setId(arrivalStationId);
            arrival.setName(StationRepository.getStationById(arrivalStationId).getName());

            route.setDeparture(departure);
            route.setArrival(arrival);

            trip.setId(tripId);
            trip.setCost(tripCost);
            trip.setDuration(DateUtils.toDateFormat(tripDuration));
            trip.setRoute(route);

            trips.add(trip);
        }
    }
}

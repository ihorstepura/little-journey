package org.vector.littlejourney.service;

import org.vector.littlejourney.database.entity.Trip;
import org.vector.littlejourney.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilter {

    private DataFilter() {
    }

    public static List<Trip> filterByRoute(List<Trip> trips, String departure, String arrival) {

        return trips
                .parallelStream()
                .filter(trip -> {

                    String depart = trip.getRoute().getDeparture().getName();
                    String arr = trip.getRoute().getArrival().getName();

                    return departure.equalsIgnoreCase(depart) && arrival.equalsIgnoreCase(arr);
                })
                .collect(Collectors.toList());
    }

    public static List<Trip> filterByPrice(List<Trip> trips, int minPrice, int maxPrice) {

        return trips
                .parallelStream()
                .filter(trip ->

                        trip.getCost() <= maxPrice && trip.getCost() >= minPrice)

                .collect(Collectors.toList());
    }

    public static List<Trip> filterByTravelTime(List<Trip> trips, Date time) {

        return trips
                .parallelStream()
                .filter(trip ->

                        DateUtils.compare(trip.getDuration(), time) <= 0

                ).collect(Collectors.toList());
    }
}

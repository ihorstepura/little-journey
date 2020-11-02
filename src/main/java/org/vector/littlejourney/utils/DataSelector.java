package org.vector.littlejourney.utils;

import org.vector.littlejourney.entity.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataSelector {

    public static List<Trip> selectByRoute(List<Trip> trips, String departure, String arrival) {

        return trips
                .parallelStream()
                .filter(trip -> {
                    String depart = trip.getRoute().getDeparture().getName();
                    String arr = trip.getRoute().getArrival().getName();

                    return departure.equalsIgnoreCase(depart) && arrival.equalsIgnoreCase(arr);
                })
                .collect(Collectors.toList());
    }

    public static List<Trip> selectByPrice(List<Trip> trips, int minPrice, int maxPrice) {

        List<Trip> selectedTrips = new ArrayList<>();

        for (Trip trip : trips) {

            if (trip.getCost() <= maxPrice && trip.getCost() >= minPrice) {

                selectedTrips.add(trip);
            }
        }

        return selectedTrips;
    }

    public static List<Trip> selectByTravelTime(List<Trip> trips, Date time) {

        List<Trip> selectedTrips = new ArrayList<>();

        for (Trip trip : trips) {

            if (DateUtils.compare(trip.getDuration(), time) >= 0) {

                selectedTrips.add(trip);
            }
        }

        return selectedTrips;
    }
}
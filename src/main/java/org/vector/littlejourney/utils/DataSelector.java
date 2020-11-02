package org.vector.littlejourney.utils;

import org.vector.littlejourney.entity.Trip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public static List<Trip> selectByTravelTime(List<Trip> trips, Date minTime, Date maxTime) {

        List<Trip> selectedTrips = new ArrayList<>();

        for (Trip trip : trips) {

            if ((trip.getDuration().compareTo(maxTime) < 0 || trip.getDuration().compareTo(maxTime) == 0) &&
                    (trip.getDuration().compareTo(minTime) > 0 || trip.getDuration().compareTo(minTime) == 0)) {

                selectedTrips.add(trip);

            }
        }

        return selectedTrips;
    }

    public static List<Trip> selectByTravelTime(List<Trip> trips, String time) {

        List<Trip> selectedTrips = new ArrayList<>();

        for (Trip trip : trips) {

            DateFormat format = new SimpleDateFormat("hh:mm");
            String ticketTime = format.format(trip.getDuration());

            if (ticketTime.compareTo(time) >= 0) {

                selectedTrips.add(trip);
            }
        }

        return selectedTrips;
    }
}
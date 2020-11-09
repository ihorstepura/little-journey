package org.vector.littlejourney.util;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;

import java.util.ArrayList;
import java.util.List;

public class InputTripsConverter {

    public static List<Trip> convert(List<String> trips, String regex) {

        List<Trip> resultTrips = new ArrayList<>();

        for (String trip : trips) {

            String[] result = trip.split(regex);

            String departure = result[0];
            String arrival = result[1];
            String cost = result[2];
            String duration = result[3];

            resultTrips.add(new Trip(new Route(new Station(departure), new Station(arrival)),
                    Double.parseDouble(cost), DateUtils.toDateFormat(duration)));
        }
        return resultTrips;
    }
}

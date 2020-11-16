package org.vector.littlejourney.service;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.file.exception.FileException;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;
import org.vector.littlejourney.util.constant.WarningConstant;
import org.vector.littlejourney.util.file.Attribute;

import java.util.ArrayList;
import java.util.List;

public class TripHelper {

    public static List<Trip> process(List<List<String>> rows, List<Attribute> attributes) throws FileException {

        if (validate(rows.remove(0), attributes)) {

            List<Trip> trips = new ArrayList<>();

            for (List<String> row : rows) {

                String departure = row.get(0);
                String arrival = row.get(1);
                String cost = row.get(2);
                String duration = row.get(3);

                trips.add(new Trip(new Route(new Station(departure), new Station(arrival)),
                        Double.parseDouble(cost), DateUtils.toDateFormat(duration)));
            }
            return trips;
        }
        throw new FileException(WarningConstant.INVALID_DATA_FORMAT);
    }

    private static boolean validate(List<String> header, List<Attribute> attributes) {

        for (Attribute attribute : attributes) {

            if (attribute.isNecessarily()) {

                if (!header.contains(attribute.getName())) return false;
            }
        }
        return true;
    }

    public static String getTripInformation(Trip trip) {

        return "Станция отправления - " + trip.getRoute().getDeparture().getName().toUpperCase() + "; " +

                "станция прибытия - " + trip.getRoute().getArrival().getName().toUpperCase() + "; " +

                "цена: " + String.format("%.2f", trip.getCost()) + "; " + "длительность маршрута: " +

                DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_HH_mm);
    }
}

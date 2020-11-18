package org.vector.littlejourney.service;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.constant.*;
import org.vector.littlejourney.exception.file.FileException;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.file.Property;

import java.util.ArrayList;
import java.util.List;

public class TripHelper {

    private TripHelper() {
    }

    public static List<Trip> process(List<List<String>> rows, List<Property> properties) throws FileException {

        if (validate(rows.remove(0), properties)) {

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

    private static boolean validate(List<String> header, List<Property> properties) {

        for (Property property : properties) {

            if (property.isNecessarily() && !header.contains(property.getName())) {

                    return false;
            }
        }
        return true;
    }

    public static String getTripInformation(Trip trip) {

        return "Станция отправления - " + trip.getRoute().getDeparture().getName().toUpperCase() + "; " +

                "станция прибытия - " + trip.getRoute().getArrival().getName().toUpperCase() + "; " +

                "цена: " + String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, trip.getCost()) + "; " +

                "длительность маршрута: " + DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_dd_HH_mm);
    }

    public static String prepareDocument(Trip trip) {

        String departure = TripConstant.DEPARTURE + StringConstant.COLON + trip.getRoute().getDeparture().getName();
        String arrival = TripConstant.ARRIVAL + StringConstant.COLON + trip.getRoute().getArrival().getName();
        String cost = TripConstant.COST + StringConstant.COLON + String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, trip.getCost());
        String duration = TripConstant.DURATION + StringConstant.COLON + DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_dd_HH_mm);

        return departure + StringConstant.COMMA
                + arrival + StringConstant.COMMA
                + cost + StringConstant.COMMA
                + duration;
    }

    public static String prepareSpreadSheet(Trip trip) {

        String departure = trip.getRoute().getDeparture().getName();
        String arrival = trip.getRoute().getArrival().getName();
        String cost = String.valueOf(String.format(FormatConstant.TWO_SYMBOLS_AFTER_POINT, trip.getCost()));
        String duration = DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_dd_HH_mm);

        return departure + FormatConstant.TAB_SYMBOL
                + arrival + FormatConstant.TAB_SYMBOL
                + cost + FormatConstant.TAB_SYMBOL
                + duration;
    }

    public static String prepareSpreadSheetHeader() {

        return TripConstant.DEPARTURE + FormatConstant.TAB_SYMBOL
                + TripConstant.ARRIVAL + FormatConstant.TAB_SYMBOL
                + TripConstant.COST + FormatConstant.TAB_SYMBOL
                + TripConstant.DURATION;
    }
}

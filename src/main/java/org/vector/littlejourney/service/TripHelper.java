package org.vector.littlejourney.service;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.exception.InvalidFileFormatException;
import org.vector.littlejourney.gui.component.dialog.ExceptionDialog;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.WarningConstant;
import org.vector.littlejourney.util.file.Attribute;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TripHelper {

    public static List<Trip> process(List<List<String>> rows, List<Attribute> attributes) {

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

        } else {
            SwingUtilities.invokeLater(new ExceptionDialog(WarningConstant.INVALID_DATA_FORMAT));

            throw new InvalidFileFormatException(WarningConstant.INVALID_DATA_FORMAT);
        }
    }

    private static boolean validate(List<String> header, List<Attribute> attributes) {

        for (Attribute attribute : attributes) {

            if (attribute.isNecessarily()) {

                if (!header.contains(attribute.getName())) {

                    return false;
                }
            }
        }
        return true;
    }
}

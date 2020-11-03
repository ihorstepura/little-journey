package org.vector.littlejourney;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.mock.TripFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        TripFactory tripFactory = TripFactory.getInstance();

        List<Trip> trips = tripFactory.generateTickets(1000);

        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }
}
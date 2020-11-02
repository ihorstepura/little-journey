package org.vector.littlejourney;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.mock.TripFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        TripFactory tripFactory = TripFactory.getInstance();

        List<Trip> trips = tripFactory.generateTickets(10_000);

        System.out.println(trips);

    }
}
package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.TripRepository;
import org.vector.littlejourney.database.entity.Trip;

public class TripService {

    private static final TripRepository tripRepository = TripRepository.getTripRepository();

    private TripService() {
    }

    public static Trip getTrip(int tripId) {

        return tripRepository.getById(tripId);
    }
}

package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.DatabaseRepository;
import org.vector.littlejourney.entity.Station;

public class StationService {

    private StationService() {
    }

    public static Station generateStation(Station station) {

        return DatabaseRepository.getStationById(station.getId());
    }
}

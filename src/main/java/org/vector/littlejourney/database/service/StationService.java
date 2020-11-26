package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.StationRepository;
import org.vector.littlejourney.entity.Station;

public class StationService {

    private static final StationRepository stationRepository = new StationRepository();

    public static Station generateStation(Station station) {

        return stationRepository.getStationById(station);
    }
}

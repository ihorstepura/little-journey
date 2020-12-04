package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.StationRepository;
import org.vector.littlejourney.entity.Station;

public class StationService {

    private static final StationRepository stationRepository = StationRepository.getStationRepository();

    private StationService() {
    }

    public static Station getStationById(int stationId) {

        return stationRepository.getById(stationId);
    }
}

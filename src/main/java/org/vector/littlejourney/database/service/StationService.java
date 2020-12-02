package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.repository.StationRepository;
import org.vector.littlejourney.entity.Station;

public class StationService {

    private StationService() {
    }

    public static Station getStationAttributes(Station station) {

        return StationRepository.getStationById(station.getId());
    }
}

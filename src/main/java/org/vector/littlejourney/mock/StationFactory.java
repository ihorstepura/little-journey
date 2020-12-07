package org.vector.littlejourney.mock;

import org.vector.littlejourney.database.entity.Station;
import org.vector.littlejourney.util.RandomDataGenerationUtils;

import java.util.*;

public class StationFactory {

    private static StationFactory station;

    private StationFactory() {
    }

    public static StationFactory getStation() {

        if (station == null) {

            station = new StationFactory();
        }
        return station;
    }

    public List<Station> generateStations(int count) {

        List<Station> stations = new ArrayList<>(count);

        List<String> cities = new MockJourney().getCity();

        for (int i = 0; i < count; i++) {

            stations.add(new Station(RandomDataGenerationUtils.getRandomElement(cities)));
        }

        return stations;
    }
}

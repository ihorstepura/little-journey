package org.vector.littlejourney.mock;

import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.util.RandomDataGenerator;

import java.util.*;

public class StationFactory {

    private static StationFactory instance;

    private StationFactory() {
    }

    public static StationFactory getInstance() {

        if (instance == null) {

            instance = new StationFactory();
        }
        return instance;
    }

    public List<Station> generateStations(int count) {

        List<Station> stations = new ArrayList<>(count);

        List<String> cities = new MockJourney().getCity();

        for (int i = 0; i < count; i++) {

            stations.add(new Station(RandomDataGenerator.randomElement(cities)));
        }

        return stations;
    }
}

package org.vector.mock;

import org.vector.Station;

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

    public List<Station> generateStations(int number) {
        return generateStation(number);
    }

    private List<Station> generateStation(int number) {

        List<Station> stations = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {

            stations.add(new Station());
        }

        for (int i = 0; i < number; i++) {

            stations.get(i).setName(randomName());
        }

        return stations;
    }

    private String randomName() {
        return DataRandomizer.randomStationName();
    }
}

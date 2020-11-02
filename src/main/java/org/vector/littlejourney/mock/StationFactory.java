package org.vector.littlejourney.mock;

import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.utils.RandomDataGenerator;

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

            stations.add(new Station(RandomDataGenerator.randomElement(MockCity.getData())));
        }

        return stations;
    }
}

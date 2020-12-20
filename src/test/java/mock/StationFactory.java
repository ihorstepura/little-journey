package mock;

import org.vector.littlejourney.dal.dao.StationEntity;

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

    public List<StationEntity> generateStations(int count) {

        List<StationEntity> stations = new ArrayList<>(count);

        List<String> cities = new MockJourney().getCity();

        for (int i = 0; i < count; i++) {

            stations.add(new StationEntity(/*RandomDataGenerationUtils.getRandomElement(cities)*/));
        }

        return stations;
    }
}

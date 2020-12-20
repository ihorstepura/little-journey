package mock;

import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.dao.StationEntity;

import java.util.ArrayList;
import java.util.List;

public class RouteFactory {

    private static RouteFactory route;

    private final StationFactory stationFactory = StationFactory.getStation();

    private RouteFactory() {

    }

    public static RouteFactory getRoute() {

        if (route == null) {

            route = new RouteFactory();
        }

        return route;
    }

    public List<RouteEntity> generateRouts(int count) {

        List<StationEntity> departments = stationFactory.generateStations(count);

        List<StationEntity> arrival = stationFactory.generateStations(count);

        List<RouteEntity> routes = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {

            routes.add(new RouteEntity(/*departments.get(i), arrival.get(i)*/));
        }

        return routes;
    }
}

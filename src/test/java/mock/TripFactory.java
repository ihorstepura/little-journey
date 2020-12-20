package mock;

import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.dao.TripEntity;

import java.util.*;

public class TripFactory {

    private static TripFactory trip;

    private final RouteFactory routeFactory = RouteFactory.getRoute();

    private TripFactory() {

    }

    public static TripFactory getTrip() {

        if (trip == null) {

            trip = new TripFactory();
        }
        return trip;
    }

    public List<TripEntity> generateTrips(int count) {

        List<RouteEntity> routes = routeFactory.generateRouts(count);

        List<TripEntity> trips = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {

            int priceFrom = 20;
            int priceTo = 1000;

            trips.add(new TripEntity(/*routes.get(i),
                    RandomDataGenerationUtils.getRandomDouble(priceFrom, priceTo),
                    RandomDataGenerationUtils.getRandomDate()*/));
        }
        return trips;
    }
}

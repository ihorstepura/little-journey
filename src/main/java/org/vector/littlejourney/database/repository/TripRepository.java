package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.RouteService;
import org.vector.littlejourney.database.service.TripHelper;
import org.vector.littlejourney.database.entity.Station;
import org.vector.littlejourney.database.entity.Trip;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class TripRepository implements CrudRepository<Trip> {

    private static TripRepository tripRepository;

    private static final Connection connection = DatabaseConnector.getConnection();

    private TripRepository() {
    }

    public static TripRepository getTripRepository() {

        if (tripRepository == null) {

            tripRepository = new TripRepository();
        }
        return tripRepository;
    }

    @Override
    public Trip getById(int tripId) {

        Trip trip = new Trip();

        String sql = "SELECT * FROM get_trip_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, tripId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                trip.setId(resultSet.getInt(1));

                trip.setCost(resultSet.getDouble(2));

                trip.setDuration(DateUtils.toDateFormat(resultSet.getString(3)));

                trip.setRoute(RouteService.getRouteById(resultSet.getInt(4)));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public Trip add(Trip trip) {

        String sql = "CALL add_trip(?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            setProcedureParameters(trip, statement);

            trip = getById(getTripIdByCostDurationRoute(trip.getCost(), trip.getDuration(),
                    trip.getRoute().getDeparture(), trip.getRoute().getArrival()));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }


    @Override
    public Trip update(Trip trip) {

        String sql = "CALL update_trip(?, ?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, trip.getId());

            statement.setDouble(2, trip.getCost());

            statement.setString(3, DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_HH_mm_ss));

            statement.setString(4, trip.getRoute().getDeparture().getName());

            statement.setString(5, trip.getRoute().getArrival().getName());

            statement.execute();

            trip = getById(getTripIdByCostDurationRoute(trip.getCost(), trip.getDuration(),
                    trip.getRoute().getDeparture(), trip.getRoute().getArrival()));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public void delete(Trip trip) {

        String sql = "CALL delete_trip(?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            setProcedureParameters(trip, statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private int getTripIdByCostDurationRoute(double cost, Date duration, Station departure, Station arrival) {

        int id = 0;

        String sql = "SELECT * FROM get_trip_id(?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setDouble(1, cost);

            statement.setString(2, DateUtils.toSimpleFormat(duration, DateConstant.DATE_FORMAT_HH_mm_ss));

            statement.setString(3, departure.getName());

            statement.setString(4, arrival.getName());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public void filterTrips(List<Trip> trips, String departure, String arrival, Double minCost, Double maxCost, Date time) {

        String sql = "SELECT * FROM filter_trips(?, ?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, departure);
            statement.setString(2, arrival);
            statement.setDouble(3, minCost);
            statement.setDouble(4, maxCost);
            statement.setString(5, DateUtils.toSimpleFormat(time, DateConstant.DATE_FORMAT_HH_mm_ss));

            ResultSet resultSet = statement.executeQuery();

            TripHelper.prepareTrip(resultSet, trips);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private void setProcedureParameters(Trip trip, CallableStatement statement) throws SQLException {
        statement.setDouble(1, trip.getCost());

        statement.setString(2, DateUtils.toSimpleFormat(trip.getDuration(),
                DateConstant.DATE_FORMAT_HH_mm_ss));

        statement.setString(3, trip.getRoute().getDeparture().getName());

        statement.setString(4, trip.getRoute().getArrival().getName());

        statement.execute();
    }
}

package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.TripHelper;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripRepository implements CrudRepository<Trip> {

    private static final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Trip get(Trip trip) {

        String sql = "SELECT * FROM get_trip(?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setDouble(1, trip.getCost());

            statement.setString(2, DateUtils.toSimpleFormat(trip.getDuration(),
                    DateConstant.DATE_FORMAT_HH_mm_ss));

            statement.setInt(3, RouteRepository.getRouteIdByStations(trip.getRoute().getDeparture(),
                    trip.getRoute().getArrival()));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                trip.setId(resultSet.getInt(1));

                trip.setCost(resultSet.getDouble(2));

                trip.setDuration(DateUtils.toDateFormat(resultSet.getString(3)));

                trip.setRoute(RouteRepository.getRouteByTripId(resultSet.getInt(4)));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public Trip add(Trip trip) {

        String sql = "CALL add_trip(?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setDouble(1, trip.getCost());

            statement.setString(2, DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_HH_mm_ss));

            statement.setInt(3, trip.getRoute().getId());

            statement.execute();

            trip = get(trip);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public Trip update(Trip trip) {

        return null;
    }

    @Override
    public void delete(Trip trip) {

        String sql = "CALL delete_trip(?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            setProcedureParameters(trip, statement);

            statement.execute();

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
    }

    public static Trip getTripById(int id) {

        Trip trip = new Trip(id);

        String sql = "SELECT * FROM get_trip_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                trip.setCost(resultSet.getDouble(1));

                trip.setDuration(DateUtils.toDateFormat(resultSet.getString(2)));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    public static List<Trip> getTrips() {

        List<Trip> trips = new ArrayList<>();

        String sql = "SELECT * FROM get_trips()";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            ResultSet resultSet = statement.executeQuery();

            TripHelper.prepareTrip(resultSet, trips);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return trips;
    }

    public static List<Trip> filterTripsByCost(List<Trip> trips, double minCost, double maxCost) {

        String sql = "SELECT * FROM filter_trips_by_cost(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setDouble(1, minCost);

            statement.setDouble(2, maxCost);

            ResultSet resultSet = statement.executeQuery();

            TripHelper.prepareTrip(resultSet, trips);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trips;
    }

    public static List<Trip> filterTripsByDuration(List<Trip> trips, Date duration) {

        String sql = "SELECT * FROM filter_trips_by_duration(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, DateUtils.toSimpleFormat(duration, DateConstant.DATE_FORMAT_HH_mm_ss));

            ResultSet resultSet = statement.executeQuery();

            TripHelper.prepareTrip(resultSet, trips);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trips;
    }

    public static List<Trip> filterTripsByRoute(List<Trip> trips, Station departure, Station arrival) {

        String sql = "SELECT * FROM filter_trips_by_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, departure.getName());

            statement.setString(2, arrival.getName());

            ResultSet resultSet = statement.executeQuery();

            TripHelper.prepareTrip(resultSet, trips);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trips;
    }

    public static List<Trip> filterTrips(List<Trip> trips, String departure, String arrival, Double minCost, Double maxCost, Date time) {

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
        return trips;
    }
}

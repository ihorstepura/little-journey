package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.EntityHelper;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;

import java.sql.*;

public class DatabaseRepository {

    private DatabaseRepository() {
    }

    private static final Connection connection = DatabaseConnector.getConnection();

    public static void filterTripsByCost(double minCost, double maxCost) {

        String SQL = "SELECT * FROM filter_by_cost(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void filterTripsByDuration(Date duration) {

        String SQL = "SELECT * FROM filter_by_duration(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, DateUtils.toSimpleFormat(duration, DateConstant.DATE_FORMAT_dd_HH_mm));

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public static void filterTripsByRoute(Station departure, Station arrival) {

        String SQL = "SELECT * FROM filter_by_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, departure.getName());

            statement.setString(2, arrival.getName());

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public static Station getStationById(int id) {

        Station station = new Station(id);

        String SQL = "SELECT * FROM get_station_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setInt(1, station.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                station.setId(resultSet.getInt(1));

                station.setName(resultSet.getString(2));
            }
            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return station;
    }

    public static int getDepartureStationId(int id) {

        String SQL = "SELECT * FROM get_departure_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            id = EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public static int getArrivalStationId(int id) {

        String SQL = "SELECT * FROM get_arrival_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            id = EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public static int getRouteId(int id) {

        String SQL = "SELECT * FROM get_route_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            id = EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public static int getRouteId(Station departure, Station arrival) {

        int routeId = 0;

        String SQL = "SELECT * FROM get_route_id(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, departure.getName());

            statement.setString(2, arrival.getName());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                routeId = resultSet.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return routeId;
    }

    public static Trip getTripById(int id) {

        Trip trip = new Trip(id);

        String SQL = "SELECT * FROM get_trip_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

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

    public static Route getRouteById(int id) {

        Route route = new Route(id);

        String SQL = "SELECT * FROM get_route_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                route.setId(resultSet.getInt(1));

                route.setDeparture(DatabaseRepository.getStationById(resultSet.getInt(2)));

                route.setArrival(DatabaseRepository.getStationById(resultSet.getInt(3)));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return route;
    }


    private static void showTrips(CallableStatement statement) throws SQLException {

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            int id = resultSet.getInt(1);
            double cost = resultSet.getDouble(2);
            String duration = resultSet.getString(3);
            int routeId = resultSet.getInt(4);

            System.out.println("id: " + id);
            System.out.println("Cost: " + cost);
            System.out.println("Duration: " + duration);
            System.out.println("routeId: " + routeId);
            System.out.println("----------------------------");
        }
    }


}

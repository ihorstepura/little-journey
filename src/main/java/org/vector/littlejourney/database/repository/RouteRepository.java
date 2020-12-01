package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.EntityHelper;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;

import java.sql.*;

public class RouteRepository implements CrudRepository<Route> {

    private static final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Route get(Route route) {

        String sql = "SELECT * FROM get_route_by_stations_name(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                route.setId(resultSet.getInt(1));

                route.setDeparture(StationRepository.getStationById(resultSet.getInt(2)));

                route.setArrival(StationRepository.getStationById(resultSet.getInt(3)));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }

    @Override
    public Route add(Route route) {

        String sql = "CALL add_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

            statement.execute();

            route = get(route);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }

    @Override
    public Route update(Route route) {

        return null;
    }

    @Override
    public void delete(Route route) {

        String sql = "CALL delete_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static int getRouteIdByTripId(int id) {

        String sql = "SELECT * FROM get_route_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            id = EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public static int getRouteIdByStations(Station departure, Station arrival) {

        int routeId = 0;

        String sql = "SELECT * FROM get_route_id(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

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

    public static Route getRouteByTripId(int id) {

        Route route = new Route(id);

        String sql = "SELECT * FROM get_route_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                route.setId(resultSet.getInt(1));

                route.setDeparture(StationRepository.getStationById(resultSet.getInt(2)));

                route.setArrival(StationRepository.getStationById(resultSet.getInt(3)));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }
}

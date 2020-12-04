package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.StationService;
import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.entity.Station;

import java.sql.*;

public class RouteRepository implements CrudRepository<Route> {

    private static RouteRepository routeRepository;

    private static final Connection connection = DatabaseConnector.getConnection();

    private RouteRepository() {
    }

    public static RouteRepository getRouteRepository() {

        if (routeRepository == null) {

            routeRepository = new RouteRepository();
        }
        return routeRepository;
    }

    @Override
    public Route getById(int routeId) {

        Route route = new Route();

        String sql = "SELECT * FROM get_route_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, routeId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                route.setId(resultSet.getInt(1));

                route.setDeparture(StationService.getStationById(resultSet.getInt(2)));

                route.setArrival(StationService.getStationById(resultSet.getInt(3)));
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

            route = getById(getRouteIdByStations(route.getDeparture(), route.getArrival()));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }

    @Override
    public Route update(Route route) {

        String sql = "CALL update_route(?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, route.getDeparture().getId());

            statement.setString(2, route.getArrival().getName());

            statement.setString(3, route.getDeparture().getName());

            statement.execute();

            route = getById(getRouteIdByStations(route.getDeparture(), route.getArrival()));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
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

    public int getRouteIdByStations(Station departure, Station arrival) {

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
}

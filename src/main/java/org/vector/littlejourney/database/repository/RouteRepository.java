package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.entity.Route;

import java.sql.*;

public class RouteRepository implements CrudRepository<Route> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Route get(Route route) {

        String SQL = "SELECT * FROM get_route_by_stations_name(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

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

    @Override
    public Route add(Route route) {

        String SQL = "CALL add_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

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

        String SQL = "CALL delete_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}

package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.entity.Route;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RouteService implements CrudRepository<Route> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Route get(int id) {

        return null;
    }

    @Override
    public Route add(Route route) {

        String SQL = "CALL add_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

            statement.execute();

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

package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.EntityHelper;
import org.vector.littlejourney.entity.Route;

import java.sql.*;

public class RouteRepository implements CrudRepository<Route> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Route get(int id) {

        return null;
    }

    @Override
    public Route add(Route route) {

        String SQL = "CALL add_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            connection.setAutoCommit(false);

            statement.setString(1, route.getDeparture().getName());

            statement.setString(2, route.getArrival().getName());

            statement.execute();

            connection.commit();

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

    public int getRouteId(int id) {

        String SQL = "SELECT * FROM get_route_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }
}

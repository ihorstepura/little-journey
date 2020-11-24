package org.vector.littlejourney.service.database;

import org.vector.littlejourney.entity.Route;
import org.vector.littlejourney.util.constant.database.DatabaseConstant;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RouteService implements DatabaseService<Route> {

    private final DatabaseConnector connector = DatabaseConnector.getConnection();

    private final Connection connection = connector.connect(DatabaseConstant.DATABASE_NAME);

    @Override
    public Route add(Route route) {

        try {
            String SQL = "CALL add_route(?, ?)";

            CallableStatement statement = connection.prepareCall(SQL);

            statement.setString(1, route.getDeparture().getName());
            statement.setString(2, route.getArrival().getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }

    @Override
    public void update(Route route) {

    }

    @Override
    public void delete(Route route) {

    }
}

package org.vector.littlejourney.service.database;

import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;
import org.vector.littlejourney.util.constant.database.DatabaseConstant;

import java.sql.*;

public class DatabaseRepository {

    private static DatabaseRepository instance;

    private final DatabaseConnector connector = DatabaseConnector.getConnection();

    private final Connection connection = connector.connect(DatabaseConstant.DATABASE_NAME);

    private DatabaseRepository() {
    }

    public static DatabaseRepository getInstance() {

        if (instance == null) {

            instance = new DatabaseRepository();
        }
        return instance;
    }

    public void filterTripsByCost(double minCost, double maxCost) {

        String SQL = "SELECT * FROM filter_by_cost(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void filterTripsByDuration(Date duration) {

        String SQL = "SELECT * FROM filter_by_duration(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, DateUtils.toSimpleFormat(duration, DateConstant.DATE_FORMAT_dd_HH_mm));

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void filterTripsByRoute(Station departure, Station arrival) {

        String SQL = "SELECT * FROM filter_by_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, departure.getName());

            statement.setString(2, arrival.getName());

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    private void showTrips(CallableStatement statement) throws SQLException {

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            int id = resultSet.getInt(1);
            double cost = resultSet.getDouble(2);
            String duration = resultSet.getString(3);
            int route_id = resultSet.getInt(4);

            System.out.println("id: " + id);
            System.out.println("Cost: " + cost);
            System.out.println("Duration: " + duration);
            System.out.println("route_id: " + route_id);
            System.out.println("----------------------------");
        }
    }
}

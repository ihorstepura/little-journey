package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;

import java.sql.*;

public class DatabaseRepository {

    private DatabaseRepository() {
    }

    private static final Connection connection = DatabaseConnector.getConnection();

    public static void filterTripsByCost(double minCost, double maxCost) {

        String sql = "SELECT * FROM filter_by_cost(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void filterTripsByDuration(Date duration) {

        String sql = "SELECT * FROM filter_by_duration(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, DateUtils.toSimpleFormat(duration, DateConstant.DATE_FORMAT_dd_HH_mm));

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void filterTripsByRoute(Station departure, Station arrival) {

        String sql = "SELECT * FROM filter_by_route(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, departure.getName());

            statement.setString(2, arrival.getName());

            showTrips(statement);

        } catch (SQLException e) {

            e.printStackTrace();
        }
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

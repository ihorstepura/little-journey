package org.vector.littlejourney.service.database;

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


        try {
            String SQL = "SELECT * FROM filter_by_cost(?, ?)";

            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                double cost = resultSet.getDouble(2);
                Date duration = resultSet.getDate(3);
                int route_id = resultSet.getInt(4);

                System.out.println("id: " + id);
                System.out.println("Cost: " + cost);
                System.out.println("Duration: " + duration);
                System.out.println("route_id: " + route_id);
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void filterTripsByDuration() {

    }


    public void filterTripsByRoute() {


    }
}

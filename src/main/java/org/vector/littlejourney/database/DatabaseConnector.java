package org.vector.littlejourney.database;

import org.vector.littlejourney.util.constant.database.DatabaseConstant;
import org.vector.littlejourney.util.exception.database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector connector;

    private DatabaseConnector() {
    }

    public static Connection getConnection() {

        if (connector == null) {

            connector = new DatabaseConnector();
        }
        return connector.connect();
    }

    private Connection connect() {

        Connection connection;

        try {
            connection = DriverManager.getConnection(
                    DatabaseConstant.DATABASE_URL,
                    DatabaseConstant.USER,
                    DatabaseConstant.PASSWORD);

        } catch (SQLException e) {

            throw new DatabaseConnectionException(DatabaseConstant.DATABASE_CONNECTION_FAILED);
        }
        return connection;
    }
}

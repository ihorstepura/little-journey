package org.vector.littlejourney.service.database;

import org.vector.littlejourney.util.constant.database.DatabaseConstant;
import org.vector.littlejourney.util.exception.database.DatabaseConnectionException;
import org.vector.littlejourney.util.exception.database.DriverNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector connector;

    private DatabaseConnector() {
    }

    public static DatabaseConnector getConnection() {

        if (connector == null) {

            connector = new DatabaseConnector();
        }
        return connector;
    }

    public Connection connect(String databaseName) {

        try {
            Class.forName(DatabaseConstant.JDBC_DRIVER);

        } catch (ClassNotFoundException e) {

            throw new DriverNotFoundException(DatabaseConstant.JDBC_DRIVER);
        }
        Connection connection;

        try {
            connection = DriverManager.getConnection
                    (
                            DatabaseConstant.DATABASE_URL + databaseName,
                            DatabaseConstant.USER,
                            DatabaseConstant.PASSWORD
                    );

        } catch (SQLException e) {

            throw new DatabaseConnectionException(DatabaseConstant.DATABASE_CONNECTION_FAILED);
        }
        return connection;
    }
}

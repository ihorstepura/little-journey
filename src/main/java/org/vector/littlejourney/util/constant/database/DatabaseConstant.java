package org.vector.littlejourney.util.constant.database;

public class DatabaseConstant {

    private DatabaseConstant() {
    }

    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/trip_db";

    public static final String JDBC_DRIVER = "org.postgresql.Driver";

    public static final String USER = "ihorstepura";

    public static final String PASSWORD = "root";

    public static final String DRIVER_NOT_FOUND = "Driver is not found";

    public static final String DATABASE_CONNECTION_FAILED = "Failed to make connection to database";
}

package org.vector.littlejourney.service.database;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;
import org.vector.littlejourney.util.constant.database.DatabaseConstant;

import java.sql.*;

public class TripService implements DatabaseService<Trip> {

    private final DatabaseConnector connector = DatabaseConnector.getConnection();

    private final Connection connection = connector.connect(DatabaseConstant.DATABASE_NAME);

    @Override
    public Trip add(Trip trip) {
        try {
            String SQL = "CALL add_trip(?, ?, ?, ?)";

            CallableStatement statement = connection.prepareCall(SQL);

            statement.setDouble(1, trip.getCost());
            statement.setString(2, DateUtils.toSimpleFormat(trip.getDuration(), DateConstant.DATE_FORMAT_dd_HH_mm));
            statement.setString(3, trip.getRoute().getDeparture().getName());
            statement.setString(4, trip.getRoute().getArrival().getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public void update(Trip trip) {

    }

    @Override
    public void delete(Trip trip) {

    }
}

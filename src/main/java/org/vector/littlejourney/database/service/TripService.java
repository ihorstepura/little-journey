package org.vector.littlejourney.database.service;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DateUtils;
import org.vector.littlejourney.util.constant.DateConstant;

import java.sql.*;

public class TripService implements CrudRepository<Trip> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Trip get(int id) {

        return null;
    }

    @Override
    public Trip add(Trip trip) {

        String SQL = "CALL add_trip(?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            setProcedureParameters(trip, statement);

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        return null;
    }

    @Override
    public void delete(Trip trip) {

        String SQL = "CALL delete_trip(?, ?, ?, ?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            setProcedureParameters(trip, statement);

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private void setProcedureParameters(Trip trip, CallableStatement statement) throws SQLException {

        statement.setDouble(1, trip.getCost());

        statement.setString(2, DateUtils.toSimpleFormat(trip.getDuration(),
                DateConstant.DATE_FORMAT_dd_HH_mm));

        statement.setString(3, trip.getRoute().getDeparture().getName());

        statement.setString(4, trip.getRoute().getArrival().getName());
    }
}

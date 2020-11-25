package org.vector.littlejourney.service.database;

import org.vector.littlejourney.entity.Station;
import org.vector.littlejourney.util.constant.database.DatabaseConstant;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StationService implements DatabaseService<Station> {

    private final DatabaseConnector connector = DatabaseConnector.getConnection();

    private final Connection connection = connector.connect(DatabaseConstant.DATABASE_NAME);

    @Override
    public Station add(Station station) {

        String SQL = "CALL add_station(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, station.getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return station;
    }

    @Override
    public void update(Station station) {

    }

    @Override
    public void delete(Station station) {

        String SQL = "CALL delete_station(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, station.getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}

package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.entity.Station;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StationRepository implements CrudRepository<Station> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Station get(int id) {

        return null;
    }

    @Override
    public Station add(Station station) {

        String SQL = "CALL add_station(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            connection.setAutoCommit(false);

            statement.setString(1, station.getName());

            statement.execute();

            connection.commit();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return station;
    }

    @Override
    public Station update(Station station) {

        return null;
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

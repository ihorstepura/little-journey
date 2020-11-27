package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.entity.Station;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationRepository implements CrudRepository<Station> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Station get(Station station) {

        String SQL = "SELECT * FROM get_station_by_name(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, station.getName());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                station.setId(resultSet.getInt(1));

                station.setName(resultSet.getString(2));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return station;
    }

    @Override
    public Station add(Station station) {

        String SQL = "CALL add_station(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setString(1, station.getName());

            statement.execute();

            station = get(station);

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

package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.EntityHelper;
import org.vector.littlejourney.entity.Station;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationRepository implements CrudRepository<Station> {

    private final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Station get(int id) {

        return null;
    }

    @Override
    public Station add(Station station) {

        return null;
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

    public Station getStationById(Station station) {

        String SQL = "SELECT * FROM get_station_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            statement.setInt(1, station.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                station.setId(resultSet.getInt(1));

                station.setName(resultSet.getString(2));
            }
            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return station;
    }

    public int getDepartureStationId(int id) {

        String SQL = "SELECT * FROM get_departure_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public int getArrivalStationId(int id) {

        String SQL = "SELECT * FROM get_arrival_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(SQL)) {

            EntityHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }
}

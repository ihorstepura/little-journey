package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.TripHelper;
import org.vector.littlejourney.entity.Station;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationRepository implements CrudRepository<Station> {

    private static StationRepository stationRepository;

    private static final Connection connection = DatabaseConnector.getConnection();

    private StationRepository() {
    }

    public static StationRepository getStationRepository() {

        if (stationRepository == null) {

            stationRepository = new StationRepository();
        }
        return stationRepository;
    }

    @Override
    public Station getById(int stationId) {

        Station station = new Station();

        String sql = "SELECT * FROM get_station_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, stationId);

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

        String sql = "CALL add_station(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, station.getName());

            statement.execute();

            station = getById(getStationIdByName(station.getName()));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return station;
    }

    @Override
    public Station update(Station station) {

        String sql = "CALL update_station(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, station.getId());

            statement.setString(2, station.getName());

            statement.execute();

            station = getById(getStationIdByName(station.getName()));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return station;
    }

    @Override
    public void delete(Station station) {

        String sql = "CALL delete_station(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, station.getName());

            statement.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private int getStationIdByName(String stationName) {

        int id = 0;

        String sql = "SELECT * FROM get_station_by_name(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, stationName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public int getDepartureStationIdByRouteId(int id) {

        String sql = "SELECT * FROM get_departure_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            id = TripHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public int getArrivalStationIdByRouteId(int id) {

        String sql = "SELECT * FROM get_arrival_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            id = TripHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }
}

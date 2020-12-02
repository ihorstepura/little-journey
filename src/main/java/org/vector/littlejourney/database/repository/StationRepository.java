package org.vector.littlejourney.database.repository;

import org.vector.littlejourney.database.DatabaseConnector;
import org.vector.littlejourney.database.service.TripHelper;
import org.vector.littlejourney.entity.Station;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationRepository implements CrudRepository<Station> {

    private static final Connection connection = DatabaseConnector.getConnection();

    @Override
    public Station get(Station station) {

        String sql = "SELECT * FROM get_station_by_name(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

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

        String sql = "CALL add_station(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

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

        String sql = "CALL update_station(?, ?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, station.getId());

            statement.setString(2, station.getName());

            statement.execute();

            station = get(station);

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

    public static Station getStationById(int id) {

        Station station = new Station(id);

        String sql = "SELECT * FROM get_station_by_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

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

    public static int getDepartureStationIdByRouteId(int id) {

        String sql = "SELECT * FROM get_departure_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            id = TripHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

    public static int getArrivalStationIdByRouteId(int id) {

        String sql = "SELECT * FROM get_arrival_station_id(?)";

        try (CallableStatement statement = connection.prepareCall(sql)) {

            id = TripHelper.getEntityId(statement, id);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }
}

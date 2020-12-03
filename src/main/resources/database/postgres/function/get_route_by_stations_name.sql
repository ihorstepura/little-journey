CREATE OR REPLACE FUNCTION get_route_by_stations_name(departure_station_name varchar, arrival_station_name varchar)
    RETURNS TABLE
            (
                id                   int,
                departure_station_id int,
                arrival_station_id   int
            )
    LANGUAGE sql
AS
$$
SELECT *
FROM route
WHERE departure_station_id = get_station_id(departure_station_name)
  AND arrival_station_id = get_station_id(arrival_station_name);
$$;
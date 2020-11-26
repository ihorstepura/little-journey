CREATE OR REPLACE FUNCTION get_route_id(departure_station varchar, arrival_station varchar)
    RETURNS int
    LANGUAGE SQL
AS
$$
SELECT id
FROM route
WHERE get_station_id(departure_station) = departure_station_id
  AND get_station_id(arrival_station) = arrival_station_id
$$;

CREATE OR REPLACE FUNCTION get_route_id(trip_id int) RETURNS int
    LANGUAGE SQL
AS
$$
SELECT route_id
FROM trip
WHERE id = trip_id;
$$;
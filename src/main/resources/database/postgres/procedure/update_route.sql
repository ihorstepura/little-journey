CREATE OR REPLACE PROCEDURE update_route(route_id int, departure_station varchar, arrival_station varchar)
    LANGUAGE sql
AS
$$
UPDATE route
SET departure_station_id = get_station_id(departure_station),
    arrival_station_id   = get_station_id(arrival_station)
WHERE id = route_id;
$$;
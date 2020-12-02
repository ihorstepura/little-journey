CREATE OR REPLACE PROCEDURE delete_route(departure_station_name varchar, arrival_station_name varchar)
    LANGUAGE sql
AS
$$
DELETE
FROM route
WHERE id = get_route_id(departure_station_name, arrival_station_name);
$$;
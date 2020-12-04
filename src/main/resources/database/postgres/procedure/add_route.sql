CREATE OR REPLACE PROCEDURE add_route(dep_station varchar, arr_station varchar)
    LANGUAGE sql
AS
$$
INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (get_station_id(dep_station), get_station_id(arr_station));
$$;
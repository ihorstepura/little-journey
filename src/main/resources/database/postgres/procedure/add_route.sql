CREATE OR REPLACE PROCEDURE add_route(dep_station_id integer, arr_station_id integer)
    LANGUAGE SQL
AS
$$
INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (dep_station_id, arr_station_id);
$$;
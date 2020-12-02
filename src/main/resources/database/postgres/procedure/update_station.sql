CREATE OR REPLACE PROCEDURE update_station(station_id int, station_name varchar)
    LANGUAGE sql
AS
$$
UPDATE station
SET name = station_name
WHERE id = station_id;
$$;
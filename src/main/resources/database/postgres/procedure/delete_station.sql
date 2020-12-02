CREATE OR REPLACE PROCEDURE delete_station(station_name varchar)
    LANGUAGE sql
AS
$$
DELETE
FROM station
WHERE name = station_name;
$$;
CREATE OR REPLACE FUNCTION get_station_by_name(station_name varchar)
    RETURNS int
    LANGUAGE sql
AS
$$
SELECT id
FROM station
WHERE name = station_name;
$$;
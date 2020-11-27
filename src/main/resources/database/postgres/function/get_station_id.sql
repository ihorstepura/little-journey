CREATE OR REPLACE FUNCTION get_station_id(station_name varchar)
    RETURNS int
    LANGUAGE SQL
AS
$$
SELECT id
FROM station
WHERE name = station_name;
$$;
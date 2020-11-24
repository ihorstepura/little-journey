CREATE OR REPLACE FUNCTION get_station_id(departure_station varchar)
    RETURNS int
    LANGUAGE SQL
AS
$$
SELECT id
FROM station
WHERE name = departure_station;
$$;
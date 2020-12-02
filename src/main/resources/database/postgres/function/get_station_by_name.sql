CREATE OR REPLACE FUNCTION get_station_by_name(station_name varchar)
    RETURNS TABLE
            (
                id   int,
                name varchar
            )
    LANGUAGE sql
AS
$$
SELECT id, name
FROM station
WHERE name = station_name;
$$;
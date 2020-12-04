CREATE OR REPLACE FUNCTION get_station_by_id(station_id int)
    RETURNS TABLE
            (
                id   int,
                name varchar
            )
    LANGUAGE sql
AS
$$
SELECT *
FROM station
WHERE id = station_id;
$$;
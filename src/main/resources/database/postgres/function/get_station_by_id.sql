CREATE OR REPLACE FUNCTION get_station_by_id(station_id int)
    RETURNS TABLE
            (
                id   int,
                name varchar
            )
    LANGUAGE SQL
AS
$$
SELECT id, name
FROM station
WHERE id = station_id;
$$;
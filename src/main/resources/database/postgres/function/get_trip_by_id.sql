CREATE OR REPLACE FUNCTION get_trip_by_id(trip_id int)
    RETURNS TABLE
            (
                id       int,
                cost     numeric,
                duration interval,
                route_id int
            )
    LANGUAGE sql
AS
$$
SELECT *
FROM trip
WHERE id = trip_id;
$$;
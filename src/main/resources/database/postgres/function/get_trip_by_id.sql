CREATE OR REPLACE FUNCTION get_trip_by_id(trip_id int)
    RETURNS TABLE
            (
                cost     numeric,
                duration interval,
                route_id int
            )
    LANGUAGE sql
AS
$$
SELECT cost, duration, route_id
FROM trip
WHERE id = trip_id;
$$;
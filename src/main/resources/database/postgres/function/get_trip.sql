CREATE OR REPLACE FUNCTION get_trip(trip_cost double precision, trip_duration varchar, in_route_id int)
    RETURNS TABLE
            (
                id       int,
                cost     numeric,
                duration interval,
                route_id int
            )
    LANGUAGE SQL
AS
$$
SELECT id, cost, duration, route_id
FROM trip
WHERE cost = trip_cost
  AND duration = cast(trip_duration AS interval)
  AND route_id = in_route_id;
$$;
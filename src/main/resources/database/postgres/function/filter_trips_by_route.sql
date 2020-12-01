CREATE OR REPLACE FUNCTION filter_trips_by_route(departure varchar, arrival varchar) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE get_route_id(departure, arrival) = route_id;
$$;
CREATE OR REPLACE FUNCTION filter_trips(departure varchar, arrival varchar, min_cost double precision,
                                        max_cost double precision, max_duration varchar) RETURNS SETOF trip
    LANGUAGE sql
AS
$$
SELECT *
FROM trip
WHERE route_id = get_route_id(departure, arrival)
  AND cost < max_cost
  AND cost > min_cost
  AND duration < cast(max_duration as interval);
$$;
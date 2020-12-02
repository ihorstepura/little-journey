CREATE OR REPLACE FUNCTION filter_trips_by_duration(max_duration varchar) RETURNS SETOF trip
    LANGUAGE sql
AS
$$
SELECT *
FROM trip
WHERE trip.duration < cast(max_duration as interval);
$$;
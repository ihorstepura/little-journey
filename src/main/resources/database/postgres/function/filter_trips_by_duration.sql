CREATE OR REPLACE FUNCTION filter_by_duration(max_duration varchar) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE trip.duration < cast(max_duration as interval);
$$;
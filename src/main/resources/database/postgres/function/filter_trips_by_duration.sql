CREATE OR REPLACE FUNCTION filter_by_duration(max_duration interval) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE trip.duration < max_duration;
$$;
CREATE OR REPLACE FUNCTION filter_trips_by_cost(minCost double precision, maxCost double precision) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE cost < maxCost
  AND cost > minCost;
$$;
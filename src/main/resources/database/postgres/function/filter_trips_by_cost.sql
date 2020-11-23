CREATE OR REPLACE FUNCTION filter_by_cost(minCost numeric, maxCost numeric) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE cost < maxCost
  AND cost > minCost;
$$;
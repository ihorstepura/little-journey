CREATE OR REPLACE FUNCTION filter_by_route(departure integer, arrival integer) RETURNS SETOF route
    LANGUAGE SQL
AS
$$
SELECT *
FROM route
WHERE departure = departure_station_id
  AND arrival = arrival_station_id;
$$;
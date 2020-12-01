CREATE OR REPLACE FUNCTION filter_by_route(departure integer, arrival integer) RETURNS SETOF route
    LANGUAGE SQL
AS
$$
SELECT *
FROM route
WHERE departure = departure_station_id
  AND arrival = arrival_station_id;
$$;

CREATE OR REPLACE FUNCTION filter_by_route(departure varchar, arrival varchar) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE get_route_id(departure, arrival) = route_id;
$$;
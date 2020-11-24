CREATE OR REPLACE FUNCTION filter_by_route(departure integer, arrival integer) RETURNS SETOF route
    LANGUAGE SQL
AS
$$
SELECT *
FROM route
WHERE departure = departure_station_id
  AND arrival = arrival_station_id;
$$;

CREATE OR REPLACE FUNCTION filter_by_route(departure varchar, arrival varchar) RETURNS SETOF route
    LANGUAGE SQL
AS
$$
SELECT *
FROM route
WHERE get_station_id(departure) = departure_station_id
  AND get_station_id(arrival) = arrival_station_id;
$$;
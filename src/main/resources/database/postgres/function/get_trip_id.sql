CREATE OR REPLACE FUNCTION get_trip_id(trip_cost double precision, trip_duration varchar, departure_station varchar,
                                       arrival_station varchar) RETURNS int
    LANGUAGE sql
AS
$$
SELECT id
FROM trip
WHERE cost = trip_cost
  AND duration = cast(trip_duration as interval)
  AND route_id = get_route_id(departure_station, arrival_station);
$$;
CREATE OR REPLACE PROCEDURE delete_trip(trip_cost double precision, trip_duration varchar,
                                        departure_station varchar, arrival_station varchar)
    LANGUAGE sql
AS
$$
DELETE
FROM trip
WHERE cost = trip_cost
  AND duration = cast(trip_duration as interval)
  AND route_id = get_route_id(departure_station, arrival_station);
$$;
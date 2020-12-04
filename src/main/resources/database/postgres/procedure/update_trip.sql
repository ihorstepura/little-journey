CREATE OR REPLACE PROCEDURE update_trip(trip_id int, trip_cost double precision, trip_duration varchar,
                                        departure_station varchar,
                                        arrival_station varchar)
    LANGUAGE sql
AS
$$
UPDATE trip
SET cost     = trip_cost,
    duration = cast(trip_duration as interval),
    route_id = get_route_id(departure_station, arrival_station)
WHERE id = trip_id;
$$;
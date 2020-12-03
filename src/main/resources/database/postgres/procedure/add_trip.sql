CREATE OR REPLACE PROCEDURE add_trip(trip_cost double precision, trip_duration varchar, departure_station varchar,
                                     arrival_station varchar)
    LANGUAGE sql
AS
$$
INSERT INTO trip (cost, duration, route_id)
VALUES (trip_cost, cast(trip_duration AS interval), get_route_id(departure_station, arrival_station));
$$;
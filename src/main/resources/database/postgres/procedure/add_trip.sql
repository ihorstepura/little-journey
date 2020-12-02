CREATE OR REPLACE PROCEDURE add_trip(trip_cost double precision, trip_duration varchar, trip_route_id integer)
    LANGUAGE sql
AS
$$
INSERT INTO trip (cost, duration, route_id)
VALUES (trip_cost, cast(trip_duration AS interval), trip_route_id);
$$;
CREATE OR REPLACE PROCEDURE add_trip(trip_cost double precision, trip_duration interval, trip_route_id integer)
    LANGUAGE SQL
AS
$$
INSERT INTO trip (cost, duration, route_id)
VALUES (trip_cost, trip_duration, trip_route_id);
$$;
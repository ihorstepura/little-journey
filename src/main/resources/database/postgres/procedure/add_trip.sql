CREATE OR REPLACE PROCEDURE add_trip(this_route_id integer, this_cost numeric, this_duration interval)
    LANGUAGE SQL
AS
$$
INSERT INTO trip (route_id, cost, duration)
VALUES (this_route_id, this_cost, this_duration);
$$;
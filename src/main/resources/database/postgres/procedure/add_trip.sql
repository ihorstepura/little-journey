CREATE OR REPLACE PROCEDURE add_trip(this_route_id integer, this_cost double precision, this_duration interval)
    LANGUAGE SQL
AS
$$
INSERT INTO trip (cost, duration, route_id)
VALUES (this_route_id, this_cost, this_duration);
$$;

CREATE OR REPLACE PROCEDURE add_trip(this_cost double precision,
                                     this_duration varchar,
                                     departure_station varchar,
                                     arrival_station varchar)
    LANGUAGE SQL
AS
$$
INSERT INTO trip (cost, duration, route_id)
VALUES (this_cost, cast(this_duration as interval),
        get_route_id(departure_station, arrival_station));
$$;
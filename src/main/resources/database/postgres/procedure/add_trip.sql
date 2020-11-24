CREATE OR REPLACE PROCEDURE add_trip(this_cost double precision, this_duration interval, this_route_id integer)
    LANGUAGE SQL
AS
$$
INSERT INTO trip (cost, duration, route_id)
VALUES (this_cost, this_duration, this_route_id);
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
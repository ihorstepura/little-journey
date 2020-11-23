CREATE OR REPLACE PROCEDURE delete_trip(trip_id integer)
    LANGUAGE SQL
AS
$$
DELETE
FROM trip
WHERE id = trip_id;
$$;

CREATE OR REPLACE PROCEDURE delete_trip(trip_cost numeric)
    LANGUAGE SQL
AS
$$
DELETE
FROM trip
WHERE cost = trip_cost;
$$;

CREATE OR REPLACE PROCEDURE delete_trip(trip_duration interval)
    LANGUAGE SQL
AS
$$
DELETE
FROM trip
WHERE duration = trip_duration;
$$;
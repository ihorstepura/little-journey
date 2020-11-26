CREATE OR REPLACE FUNCTION get_departure_station_id(route_id int)
    RETURNS int
    LANGUAGE SQL
AS
$$
SELECT departure_station_id
FROM route
WHERE id = route_id;
$$;
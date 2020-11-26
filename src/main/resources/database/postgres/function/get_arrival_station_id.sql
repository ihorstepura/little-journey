CREATE OR REPLACE FUNCTION get_arrival_station_id(route_id int)
    RETURNS int
    LANGUAGE SQL
AS
$$
SELECT arrival_station_id
FROM route
WHERE id = route_id;
$$;
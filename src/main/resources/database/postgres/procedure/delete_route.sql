CREATE OR REPLACE PROCEDURE delete_route(route_id integer)
    LANGUAGE SQL
AS
$$
DELETE
FROM route
WHERE id = route_id;
$$;
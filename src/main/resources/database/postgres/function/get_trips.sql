CREATE OR REPLACE FUNCTION get_trips() RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip;
$$;
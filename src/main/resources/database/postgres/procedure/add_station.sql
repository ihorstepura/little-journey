CREATE OR REPLACE PROCEDURE add_station(station_name varchar)
    LANGUAGE sql
AS
$$
INSERT INTO station (name)
VALUES (station_name);
$$;
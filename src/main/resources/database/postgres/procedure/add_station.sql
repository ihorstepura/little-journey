CREATE OR REPLACE PROCEDURE add_station(station_name varchar)
    LANGUAGE SQL
AS
$$
INSERT INTO station (name)
VALUES (station_name);
$$;
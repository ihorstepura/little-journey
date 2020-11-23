CREATE OR REPLACE PROCEDURE delete_station(station_name varchar)
    LANGUAGE SQL
AS
$$
DELETE
FROM station
WHERE name = station_name;
$$;

CREATE OR REPLACE PROCEDURE delete_station(station_id integer)
    LANGUAGE SQL
AS
$$
DELETE
FROM station
WHERE id = station_id;
$$;
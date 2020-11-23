CREATE DATABASE trip_db;

CREATE TABLE station
(
    id   SERIAL      NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE route
(
    id                   SERIAL  NOT NULL PRIMARY KEY,
    departure_station_id INTEGER NOT NULL,
    arrival_station_id   INTEGER NOT NULL,
    FOREIGN KEY (departure_station_id) REFERENCES station (id),
    FOREIGN KEY (arrival_station_id) REFERENCES station (id)
);

CREATE TABLE trip
(
    id       SERIAL        NOT NULL PRIMARY KEY,
    cost     NUMERIC(8, 2) NOT NULL,
    duration INTERVAL      NOT NULL,
    route_id INTEGER       NOT NULL,
    FOREIGN KEY (route_id) REFERENCES route (id)
);

INSERT INTO station (name)
VALUES ('Киев');
INSERT INTO station (name)
VALUES ('Львов');
INSERT INTO station (name)
VALUES ('Харьков');
INSERT INTO station (name)
VALUES ('Одесса');
INSERT INTO station (name)
VALUES ('Днепр');
INSERT INTO station (name)
VALUES ('Херсон');

INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (3, 1);
INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (1, 2);

INSERT INTO trip (route_id, cost, duration)
VALUES (1, 250, '01:23:54');
INSERT INTO trip (route_id, cost, duration)
VALUES (2, 320, '02:23:54');

-- show all entities
DROP VIEW IF EXISTS show_all_entities;

CREATE VIEW show_all_entities AS
SELECT trip.id,
       trip.cost,
       trip.duration,
       route.departure_station_id,
       route.arrival_station_id
FROM trip
         INNER JOIN route ON trip.id = route.id;

SELECT *
FROM show_all_entities;

--filter by cost procedure
DROP FUNCTION IF EXISTS filter_by_cost(minCost numeric, maxCost numeric);

CREATE FUNCTION filter_by_cost(minCost numeric, maxCost numeric) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE cost < maxCost
  AND cost > minCost;
$$;

SELECT *
FROM filter_by_cost(100.00, 500.00);

--filter by route
DROP FUNCTION IF EXISTS filter_by_route(departure varchar, arrival varchar);

CREATE FUNCTION filter_by_route(departure integer, arrival integer) RETURNS SETOF route
    LANGUAGE SQL
AS
$$
SELECT *
FROM route
WHERE departure = departure_station_id
  AND arrival = arrival_station_id;
$$;

SELECT *
FROM filter_by_route(1, 2);

--filter by duration
DROP FUNCTION IF EXISTS filter_by_duration(duration timestamp);

CREATE FUNCTION filter_by_duration(max_duration interval) RETURNS SETOF trip
    LANGUAGE SQL
AS
$$
SELECT *
FROM trip
WHERE trip.duration < max_duration;
$$;

SELECT *
FROM filter_by_duration('02:12:00');

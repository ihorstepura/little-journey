CREATE TABLE IF NOT EXISTS station
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT station_name_length CHECK (char_length(name) >= 3),
    CONSTRAINT station_name_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS route
(
    id                   SERIAL PRIMARY KEY,
    departure_station_id INTEGER NOT NULL,
    arrival_station_id   INTEGER NOT NULL,
    CONSTRAINT departure_is_equal_arrival CHECK (departure_station_id != arrival_station_id),
    FOREIGN KEY (departure_station_id) REFERENCES station (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (arrival_station_id) REFERENCES station (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS trip
(
    id       SERIAL PRIMARY KEY,
    cost     NUMERIC(8, 2) NOT NULL,
    duration INTERVAL      NOT NULL,
    route_id INTEGER       NOT NULL,
    CONSTRAINT positive_cost CHECK (cost >= 0),
    FOREIGN KEY (route_id) REFERENCES route (id) ON DELETE CASCADE ON UPDATE CASCADE
);
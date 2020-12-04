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
INSERT INTO station (name)
VALUES ('Захалустье');

INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (3, 1);
INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (1, 2);
INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (4, 1);
INSERT INTO route (departure_station_id, arrival_station_id)
VALUES (5, 2);

INSERT INTO trip (cost, duration, route_id)
VALUES (250, '01:02:00', 1);
INSERT INTO trip (cost, duration, route_id)
VALUES (325.25, '02:02:00', 2);
INSERT INTO trip (cost, duration, route_id)
VALUES (112.35, '03:02:00', 3);
INSERT INTO trip (cost, duration, route_id)
VALUES (458, '05:02:00', 4);
INSERT INTO trip (cost, duration, route_id)
VALUES (100.25, '02:02:00', 2);

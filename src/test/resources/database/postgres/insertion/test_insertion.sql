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

INSERT INTO trip (route_id, cost, duration)
VALUES (1, 250, '01:23:00');
INSERT INTO trip (route_id, cost, duration)
VALUES (2, 320, '02:23:05');
INSERT INTO trip (route_id, cost, duration)
VALUES (3, 250, '05:23:02');
INSERT INTO trip (route_id, cost, duration)
VALUES (4, 320, '02:13:02');
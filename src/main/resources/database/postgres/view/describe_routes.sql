CREATE OR REPLACE VIEW describe_routs AS

SELECT route.id,
       departure_station.name AS departure_station,
       arrival_station.name   AS arrival_station
FROM route
         LEFT JOIN station AS departure_station ON route.departure_station_id = departure_station.id
         LEFT JOIN station AS arrival_station ON route.arrival_station_id = arrival_station.id;
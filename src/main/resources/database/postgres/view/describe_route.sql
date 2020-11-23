CREATE OR REPLACE VIEW describe_trips AS

SELECT route.id,
       departure_station.name,
       arrival_station.name
FROM route
         LEFT JOIN station AS departure_station ON route.departure_station_id = departure_station.id
         LEFT JOIN station AS arrival_station ON route.arrival_station_id = arrival_station.id;
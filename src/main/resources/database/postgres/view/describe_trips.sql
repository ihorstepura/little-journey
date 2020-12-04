CREATE OR REPLACE VIEW describe_trips AS

SELECT trip.id,
       trip.cost,
       trip.duration,
       departure_station.name AS departure_station,
       arrival_station.name AS arrival_station
FROM trip
         INNER JOIN route ON trip.id = route.id
         LEFT JOIN station AS departure_station ON route.departure_station_id = departure_station.id
         LEFT JOIN station AS arrival_station ON route.arrival_station_id = arrival_station.id;
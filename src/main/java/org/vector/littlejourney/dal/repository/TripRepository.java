package org.vector.littlejourney.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.dao.TripEntity;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    Optional<List<TripEntity>> findByRouteDepartureStation(StationEntity departureStation);

    Optional<List<TripEntity>> findByCostBetween(Double minCost, Double maxCost);

    Optional<List<TripEntity>> findByDurationBetween(String minDuration, String maxDuration);
}

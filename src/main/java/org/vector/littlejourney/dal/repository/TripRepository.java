package org.vector.littlejourney.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.dao.TripEntity;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    // ????
    Optional<List<TripEntity>> findByRouteDepartureStation(StationEntity departureStation);

    Optional<List<TripEntity>> findByCostBetween(Double minCost, Double maxCost);

    Optional<List<TripEntity>> findByDurationBetween(String minDuration, String maxDuration);

    @Query(value = "SELECT * FROM trip WHERE cost > :minCost AND cost < :maxCost", nativeQuery = true)
    List<TripEntity> findTripEntitiesByCostBetween(@Param("minCost") Double minCost, @Param("maxCost") Double maxCost);
}

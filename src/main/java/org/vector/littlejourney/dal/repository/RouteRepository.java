package org.vector.littlejourney.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.dao.StationEntity;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

    Optional<RouteEntity> findByDepartureStation(StationEntity station);
}

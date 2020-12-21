package org.vector.littlejourney.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vector.littlejourney.dal.dao.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
}

package org.vector.littlejourney.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vector.littlejourney.dal.dao.TripEntity;

public interface TripRepository extends JpaRepository<TripEntity, Long> {
}

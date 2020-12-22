package org.vector.littlejourney.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vector.littlejourney.dal.dao.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity, Long> {
}

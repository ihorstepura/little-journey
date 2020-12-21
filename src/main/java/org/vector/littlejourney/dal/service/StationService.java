package org.vector.littlejourney.dal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.repository.StationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StationService implements PersistenceService<StationEntity> {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<StationEntity> findAll() {

        return stationRepository.findAll();
    }

    @Override
    public StationEntity findById(Long id) {

        return stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station was not found by id " + id));
    }

    @Override
    public void add(StationEntity station) {

        stationRepository.save(station);
    }

    @Override
    public void update(StationEntity station) {

        stationRepository.save(station);
    }

    @Override
    public void delete(Long id) {

        stationRepository.deleteById(id);
    }
}

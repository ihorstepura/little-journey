package org.vector.littlejourney.dal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.dao.TripEntity;
import org.vector.littlejourney.dal.repository.TripRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TripService implements PersistenceService<TripEntity> {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<TripEntity> findAll() {

        return tripRepository.findAll();
    }

    @Override
    public TripEntity findById(Long id) {

        return tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip was not found by id " + id));
    }

    @Override
    public void add(TripEntity trip) {

        tripRepository.save(trip);
    }

    @Override
    public void update(TripEntity trip) {

        tripRepository.save(trip);
    }

    @Override
    public void delete(Long id) {

        tripRepository.deleteById(id);
    }
}

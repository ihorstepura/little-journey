package org.vector.littlejourney.dal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.dao.StationEntity;
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

        return tripRepository.getOne(id);
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

    public List<TripEntity> findByCostBetween(Double minCost, Double maxCost) {

        return tripRepository.findByCostBetween(minCost, maxCost)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Trip was not found by minCost " + minCost + " and maxCost " + maxCost));
    }

    public List<TripEntity> findByDurationBetween(String minDuration, String maxDuration) {

        return tripRepository.findByDurationBetween(minDuration, maxDuration)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Trip was not found by minDuration " + minDuration + " and maxDuration " + maxDuration));
    }

    public List<TripEntity> findByDepartureStation(StationEntity stationEntity) {

        return tripRepository.findByRouteDepartureStation(stationEntity)
                .orElseThrow(() -> new EntityNotFoundException("Trip was not found by departureStation " + stationEntity));
    }
}

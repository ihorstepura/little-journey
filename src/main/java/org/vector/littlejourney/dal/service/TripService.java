package org.vector.littlejourney.dal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.dao.TripEntity;
import org.vector.littlejourney.dal.repository.TripRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
public class TripService implements PersistenceService<TripEntity> {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public TripEntity getById(Long id) {
        log.info("Finding trip with id {}", id);

        return tripRepository.getOne(id);
    }

    @Override
    public List<TripEntity> getAll() {
        log.info("Finding all trips");

        return tripRepository.findAll();
    }

    @Override
    public TripEntity insert(TripEntity trip) {
        log.info("Insertion trip {}", trip);

        return tripRepository.save(trip);
    }

    @Override
    public TripEntity update(TripEntity trip) {
        log.info("Updating trip {}", trip);

        return tripRepository.save(trip);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting trip {}", id);

        tripRepository.deleteById(id);
    }

    public List<TripEntity> findByCostBetween(Double minCost, Double maxCost) {
        log.info("Finding trip with minimal cost {} and maximal cost {}", minCost, maxCost);

        return tripRepository.findByCostBetween(minCost, maxCost)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Trip was not found by minCost " + minCost + " and maxCost " + maxCost));
    }

    public List<TripEntity> findByDurationBetween(String minDuration, String maxDuration) {
        log.info("Finding trip with minimal duration {} and maximal duration {}", minDuration, maxDuration);

        return tripRepository.findByDurationBetween(minDuration, maxDuration)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Trip was not found by minDuration " + minDuration + " and maxDuration " + maxDuration));
    }

    public List<TripEntity> findByDepartureStation(StationEntity departureStation) {
        log.info("Finding trip with departure station {}", departureStation);

        return tripRepository.findByRouteDepartureStation(departureStation)
                .orElseThrow(() -> new EntityNotFoundException("Trip was not found by departureStation " + departureStation));
    }

    public List<TripEntity> findTripEntitiesByCostBetween(Double minCost, Double maxCost) {
        log.info("Finding trip with minimal cost {} and maximal cost {}", minCost, maxCost);

        return tripRepository.findTripEntitiesByCostBetween(minCost, maxCost);
    }
}

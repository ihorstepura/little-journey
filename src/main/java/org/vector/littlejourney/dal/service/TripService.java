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
    public List<TripEntity> findAll() {
        log.info("IN TripRepository findAll");

        return tripRepository.findAll();
    }

    @Override
    public TripEntity findById(Long id) {
        log.info("IN TripRepository findById {}", id);

        return tripRepository.getOne(id);
    }

    @Override
    public void add(TripEntity trip) {
        log.info("IN TripRepository add {}", trip);

        tripRepository.save(trip);
    }

    @Override
    public void update(TripEntity trip) {
        log.info("IN TripRepository update {}", trip);

        tripRepository.save(trip);
    }

    @Override
    public void delete(Long id) {
        log.info("IN TripRepository delete {}", id);

        tripRepository.deleteById(id);
    }

    public List<TripEntity> findByCostBetween(Double minCost, Double maxCost) {
        log.info("IN TripRepository findByCostBetween {} {}", minCost, maxCost);

        return tripRepository.findByCostBetween(minCost, maxCost)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Trip was not found by minCost " + minCost + " and maxCost " + maxCost));
    }

    public List<TripEntity> findByDurationBetween(String minDuration, String maxDuration) {
        log.info("IN TripRepository findByDurationBetween {} {}", minDuration, maxDuration);

        return tripRepository.findByDurationBetween(minDuration, maxDuration)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Trip was not found by minDuration " + minDuration + " and maxDuration " + maxDuration));
    }

    public List<TripEntity> findByDepartureStation(StationEntity stationEntity) {
        log.info("IN TripRepository findByDepartureStation {}", stationEntity);

        return tripRepository.findByRouteDepartureStation(stationEntity)
                .orElseThrow(() -> new EntityNotFoundException("Trip was not found by departureStation " + stationEntity));
    }

    public List<TripEntity> findTripEntitiesByCostBetween(Double minCost, Double maxCost) {
        log.info("IN TripRepository findTripEntitiesByCostBetween {} {}", minCost, maxCost);

        return tripRepository.findTripEntitiesByCostBetween(minCost, maxCost);
    }
}

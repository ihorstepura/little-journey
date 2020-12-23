package org.vector.littlejourney.dal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.constant.matcher.MatcherConst;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.repository.StationRepository;

import java.util.List;

@Slf4j
@Service
public class StationService implements PersistenceService<StationEntity> {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public StationEntity getById(Long id) {
        log.info("Finding station with id {}", id);

        return stationRepository.getOne(id);
    }

    @Override
    public List<StationEntity> getAll() {
        log.info("Finding all stations");

        return stationRepository.findAll();
    }

    @Override
    public StationEntity insert(StationEntity station) {
        log.info("Insertion station {}", station);

        return stationRepository.save(station);
    }

    @Override
    public StationEntity update(StationEntity station) {
        log.info("Updating station {}", station);

        return stationRepository.save(station);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting station with id {}", id);

        stationRepository.deleteById(id);
    }

    public List<StationEntity> findStationsWithExampleMatcher(String stationName) {
        log.info("Finding stations with ExampleMatcher");

        StationEntity stationEntity = new StationEntity();

        stationEntity.setName(stationName);

        Example<StationEntity> example = Example.of(stationEntity, MatcherConst.matcherIgnoreCase);

        return stationRepository.findAll(example);
    }
}

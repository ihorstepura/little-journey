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
    public List<StationEntity> findAll() {
        log.info("IN StationService findAll");

        return stationRepository.findAll();
    }

    @Override
    public StationEntity findById(Long id) {
        log.info("IN StationService findById {}", id);

        return stationRepository.getOne(id);
    }

    @Override
    public void add(StationEntity station) {
        log.info("IN StationService add {}", station);

        stationRepository.save(station);
    }

    @Override
    public void update(StationEntity station) {
        log.info("IN StationService update {}", station);

        stationRepository.save(station);
    }

    @Override
    public void delete(Long id) {
        log.info("IN StationService delete {}", id);

        stationRepository.deleteById(id);
    }

    public List<StationEntity> findStationsWithExampleMatcher(String stationName) {

        StationEntity stationEntity = new StationEntity();
        stationEntity.setName(stationName);

        Example<StationEntity> example = Example.of(stationEntity, MatcherConst.matcherEndsWith);

        return stationRepository.findAll(example);
    }
}

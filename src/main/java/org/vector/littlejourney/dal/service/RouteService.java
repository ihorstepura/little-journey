package org.vector.littlejourney.dal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.repository.RouteRepository;

import java.util.List;

@Slf4j
@Service
public class RouteService implements PersistenceService<RouteEntity> {

    @Autowired
    private RouteRepository routeRepository;

    @Override

    public List<RouteEntity> findAll() {
        log.info("IN RouteService findAll");

        return routeRepository.findAll();
    }

    @Override
    public RouteEntity findById(Long id) {
        log.info("IN RouteService findById {}", id);

        return routeRepository.getOne(id);
    }

    @Override
    public void add(RouteEntity route) {
        log.info("IN RouteService add {}", route);

        routeRepository.save(route);
    }

    @Override
    public void update(RouteEntity route) {
        log.info("IN RouteService update {}", route);

        routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {
        log.info("IN RouteService delete {}", id);

        routeRepository.deleteById(id);
    }
}

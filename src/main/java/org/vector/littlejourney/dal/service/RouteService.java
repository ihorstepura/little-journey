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
    public RouteEntity getById(Long id) {
        log.info("Finding route with id {}", id);

        return routeRepository.getOne(id);
    }

    @Override
    public List<RouteEntity> getAll() {
        log.info("Finding all routes");

        return routeRepository.findAll();
    }

    @Override
    public RouteEntity insert(RouteEntity route) {
        log.info("Insertion route {}", route);

        return routeRepository.save(route);
    }

    @Override
    public RouteEntity update(RouteEntity route) {
        log.info("Updating route {}", route);

        return routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting route with id {}", id);

        routeRepository.deleteById(id);
    }
}

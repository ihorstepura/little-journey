package org.vector.littlejourney.dal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.repository.RouteRepository;

import java.util.List;

@Service
public class RouteService implements PersistenceService<RouteEntity> {

    @Autowired
    private RouteRepository routeRepository;

    @Override

    public List<RouteEntity> findAll() {

        return routeRepository.findAll();
    }

    @Override
    public RouteEntity findById(Long id) {

        return routeRepository.getOne(id);
    }

    @Override
    public void add(RouteEntity route) {

        routeRepository.save(route);
    }

    @Override
    public void update(RouteEntity route) {

        routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {

        routeRepository.deleteById(id);
    }
}

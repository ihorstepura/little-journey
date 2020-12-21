package org.vector.littlejourney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vector.littlejourney.api.dto.RouteModel;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.service.MapService;
import org.vector.littlejourney.dal.service.RouteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<List<RouteModel>> findAllRoutes() {

        List<RouteModel> routeModels = MapService
                .convertRouteEntityToRouteModel(routeService.findAll());

        if (routeModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(routeModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteModel> findRouteById(@PathVariable("id") Long routeId) {

        if (routeId == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RouteModel routeModel = MapService
                .convertRouteEntityToRouteModel(routeService.findById(routeId));

        return new ResponseEntity<>(routeModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteModel> addRoute(@RequestBody @Valid RouteModel route) {

        if (route == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RouteEntity routeEntity = MapService.convertRouteModelToRouteEntity(route);

        routeService.add(routeEntity);

        route.setId(routeEntity.getId());

        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RouteModel> updateRoute(@RequestBody @Valid RouteModel route) {

        if (route == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RouteEntity routeEntity = MapService.convertRouteModelToRouteEntity(route);

        routeService.update(routeEntity);

        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RouteModel> deleteRoute(@PathVariable("id") Long routeId) {

        RouteEntity route = routeService.findById(routeId);

        if (route == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        routeService.delete(routeId);

        return new ResponseEntity<>(MapService.convertRouteEntityToRouteModel(route), HttpStatus.NO_CONTENT);
    }
}

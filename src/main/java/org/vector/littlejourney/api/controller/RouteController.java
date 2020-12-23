package org.vector.littlejourney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vector.littlejourney.api.dto.RouteModel;
import org.vector.littlejourney.converter.RouteConvertService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/routes")
public class RouteController {

    @Autowired
    private RouteConvertService routeConvertService;

    @GetMapping
    public ResponseEntity<List<RouteModel>> findAllRoutes() {

        List<RouteModel> routeModels = routeConvertService.getAll();

        if (routeModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(routeModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteModel> findRouteById(@PathVariable("id") Long routeId) {

        RouteModel routeModel = routeConvertService.getById(routeId);

        return new ResponseEntity<>(routeModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteModel> addRoute(@RequestBody @Valid RouteModel routeModel) {

        RouteModel route = routeConvertService.insert(routeModel);

        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RouteModel> updateRoute(@RequestBody @Valid RouteModel routeModel) {

        routeConvertService.update(routeModel);

        return new ResponseEntity<>(routeModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RouteModel> deleteRoute(@PathVariable("id") Long routeId) {

        RouteModel routeModel = routeConvertService.delete(routeId);

        return new ResponseEntity<>(routeModel, HttpStatus.NO_CONTENT);
    }
}

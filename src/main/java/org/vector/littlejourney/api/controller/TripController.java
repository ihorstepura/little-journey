package org.vector.littlejourney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vector.littlejourney.api.dto.TripModel;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.dao.TripEntity;
import org.vector.littlejourney.dal.service.MapService;
import org.vector.littlejourney.dal.service.TripService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripModel>> findAllTrips() {

        List<TripModel> tripModels = MapService
                .convertTripEntityToTripModel(tripService.findAll());

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripModel> findTripById(@PathVariable("id") Long tripId) {

        if (tripId == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TripEntity tripEntity = tripService.findById(tripId);

        TripModel tripModel = MapService
                .convertTripEntityToTripModel(tripEntity);

        return new ResponseEntity<>(tripModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TripModel> addTrip(@RequestBody @Valid TripModel trip) {

        if (trip == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TripEntity tripEntity = MapService.convertTripModelToTripEntity(trip);

        tripService.add(tripEntity);

        trip.setId(tripEntity.getId());

        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TripModel> updateTrip(@RequestBody @Valid TripModel trip) {

        if (trip == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TripEntity tripEntity = MapService.convertTripModelToTripEntity(trip);

        tripService.update(tripEntity);

        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TripModel> deleteRoute(@PathVariable("id") Long tripId) {

        TripEntity trip = tripService.findById(tripId);

        if (trip == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tripService.delete(tripId);

        return new ResponseEntity<>(MapService.convertTripEntityToTripModel(trip), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/minCost/{minCost}/maxCost/{maxCost}")
    public ResponseEntity<List<TripModel>> findTripByCostBetween(@PathVariable Double minCost,
                                                                 @PathVariable Double maxCost) {

        List<TripModel> tripModels = MapService
                .convertTripEntityToTripModel(tripService.findByCostBetween(minCost, maxCost));

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/minDuration/{minDuration}/maxDuration/{maxDuration}")
    public ResponseEntity<List<TripModel>> findTripByDurationBetween(@PathVariable String minDuration,
                                                                     @PathVariable String maxDuration) {

        List<TripModel> tripModels = MapService
                .convertTripEntityToTripModel(tripService.findByDurationBetween(minDuration, maxDuration));

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/departureStation/{departureStation}")
    public ResponseEntity<List<TripModel>> findTripByDurationBetween(@PathVariable StationEntity departureStation) {

        List<TripModel> tripModels = MapService
                .convertTripEntityToTripModel(tripService.findByDepartureStation(departureStation));

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }
}

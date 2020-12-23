package org.vector.littlejourney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vector.littlejourney.api.dto.TripModel;
import org.vector.littlejourney.converter.TripConvertService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trips")
public class TripController {

    @Autowired
    private TripConvertService tripConvertService;

    @GetMapping
    public ResponseEntity<List<TripModel>> findAllTrips() {

        List<TripModel> tripModels = tripConvertService.getAll();

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripModel> findTripById(@PathVariable("id") Long tripId) {

        TripModel tripModel = tripConvertService.getById(tripId);

        return new ResponseEntity<>(tripModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TripModel> addTrip(@RequestBody @Valid TripModel tripModel) {

        TripModel trip = tripConvertService.insert(tripModel);

        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TripModel> updateTrip(@RequestBody @Valid TripModel tripModel) {

        tripConvertService.update(tripModel);

        return new ResponseEntity<>(tripModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TripModel> deleteRoute(@PathVariable("id") Long tripId) {

        TripModel tripModel = tripConvertService.delete(tripId);

        return new ResponseEntity<>(tripModel, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tripByCost")
    public ResponseEntity<List<TripModel>> findTripByCostBetween(@RequestParam(name = "minCost") Double minCost,
                                                                 @RequestParam(name = "maxCost") Double maxCost) {

        List<TripModel> tripModels = tripConvertService.findByCostBetween(minCost, maxCost);

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/tripByDuration")
    public ResponseEntity<List<TripModel>> findTripByDurationBetween(
            @RequestParam(name = "minDuration") String minDuration,
            @RequestParam(name = "maxDuration") String maxDuration) {

        List<TripModel> tripModels = tripConvertService.findByDurationBetween(minDuration, maxDuration);

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/tripByDepartureStation")
    public ResponseEntity<List<TripModel>> findTripByDepartureStation(
            @RequestParam(name = "departureStationName") String departureStationName) {

        List<TripModel> tripModels = tripConvertService.findByDepartureStation(departureStationName);

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }

    @GetMapping("/tripByCostQuery")
    public ResponseEntity<List<TripModel>> findTripEntitiesByCostBetween(@RequestParam("minCost") Double minCost,
                                                                         @RequestParam("maxCost") Double maxCost) {

        List<TripModel> tripModels = tripConvertService.findTripEntitiesByCostBetween(minCost, maxCost);

        if (tripModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripModels, HttpStatus.OK);
    }
}

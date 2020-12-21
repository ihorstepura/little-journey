package org.vector.littlejourney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vector.littlejourney.api.dto.StationModel;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.service.MapService;
import org.vector.littlejourney.dal.service.StationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public ResponseEntity<List<StationModel>> findAllStations() {

        List<StationModel> stationModels = MapService
                .convertStationEntityToStationModel(stationService.findAll());

        if (stationModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stationModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationModel> findStationById(@PathVariable("id") Long stationId) {

        if (stationId == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        StationEntity stationEntity = stationService.findById(stationId);

        StationModel stationModel = MapService
                .convertStationEntityToStationModel(stationEntity);

        return new ResponseEntity<>(stationModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StationModel> addStation(@RequestBody @Valid StationModel station) {

        if (station == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        StationEntity stationEntity = MapService.convertStationModelToStationEntity(station);

        stationService.add(stationEntity);

        station.setId(stationEntity.getId());

        return new ResponseEntity<>(station, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StationModel> updateStation(@RequestBody @Valid StationModel station) {

        if (station == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        StationEntity stationEntity = MapService.convertStationModelToStationEntity(station);

        stationService.update(stationEntity);

        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StationModel> deleteStation(@PathVariable("id") Long stationId) {

        StationEntity station = stationService.findById(stationId);

        if (station == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stationService.delete(stationId);

        return new ResponseEntity<>(MapService.convertStationEntityToStationModel(station), HttpStatus.NO_CONTENT);
    }
}
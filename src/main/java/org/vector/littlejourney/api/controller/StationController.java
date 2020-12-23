package org.vector.littlejourney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vector.littlejourney.api.dto.StationModel;
import org.vector.littlejourney.converter.StationConvertService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/stations")
public class StationController {

    @Autowired
    private StationConvertService stationConvertService;

    @GetMapping
    public ResponseEntity<List<StationModel>> findAllStations() {

        List<StationModel> stationModels = stationConvertService.getAll();

        if (stationModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stationModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationModel> findStationById(@PathVariable(value = "id") Long stationId) {

        StationModel stationModel = stationConvertService.getById(stationId);

        return new ResponseEntity<>(stationModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StationModel> addStation(@RequestBody @Valid StationModel stationModel) {

        StationModel station = stationConvertService.insert(stationModel);

        return new ResponseEntity<>(station, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StationModel> updateStation(@RequestBody @Valid StationModel stationModel) {

        stationConvertService.update(stationModel);

        return new ResponseEntity<>(stationModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StationModel> deleteStation(@PathVariable("id") Long stationId) {

        StationModel stationModel = stationConvertService.delete(stationId);

        return new ResponseEntity<>(stationModel, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/stationByName")
    public ResponseEntity<List<StationModel>> findStationByName(@RequestParam(name = "name") String name) {

        List<StationModel> stationModels =
                stationConvertService.findStationsWithExampleMatcher(name);

        if (stationModels.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stationModels, HttpStatus.OK);
    }
}
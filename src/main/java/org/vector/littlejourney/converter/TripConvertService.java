package org.vector.littlejourney.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.api.dto.RouteModel;
import org.vector.littlejourney.api.dto.TripModel;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.dao.TripEntity;
import org.vector.littlejourney.dal.service.TripService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripConvertService {

    @Autowired
    private TripService tripService;

    @Autowired
    private StationConvertService stationConvertService;

    @Autowired
    private RouteConvertService routeConvertService;

    public TripModel getById(Long id) {

        return convertTripEntityToTripModel(tripService.getById(id));
    }

    public List<TripModel> getAll() {

        return tripService.getAll()
                .stream()
                .map(this::convertTripEntityToTripModel)
                .collect(Collectors.toList());
    }

    public TripModel insert(TripModel tripModel) {

        TripEntity tripEntity = tripService.insert(convertTripModelToTripEntity(tripModel));

        tripModel.setId(tripEntity.getId());

        return tripModel;
    }

    public TripModel update(TripModel tripModel) {

        tripService.update(convertTripModelToTripEntity(tripModel));

        return tripModel;
    }

    public TripModel delete(Long id) {

        TripModel tripModel = convertTripEntityToTripModel(tripService.getById(id));

        tripService.delete(id);

        return tripModel;
    }

    public List<TripModel> findByCostBetween(Double minCost, Double maxCost) {

        return tripService.findByCostBetween(minCost, maxCost)
                .stream()
                .map(this::convertTripEntityToTripModel)
                .collect(Collectors.toList());
    }

    public List<TripModel> findByDurationBetween(String minDuration, String maxDuration) {

        return tripService.findByDurationBetween(minDuration, maxDuration)
                .stream()
                .map(this::convertTripEntityToTripModel)
                .collect(Collectors.toList());
    }

    public List<TripModel> findByDepartureStation(String departureStationName) {

        return tripService.findByDepartureStation(departureStationName)
                .stream()
                .map(this::convertTripEntityToTripModel)
                .collect(Collectors.toList());
    }

    public List<TripModel> findTripEntitiesByCostBetween(Double minCost, Double maxCost) {

        return tripService.findTripEntitiesByCostBetween(minCost, maxCost)
                .stream()
                .map(this::convertTripEntityToTripModel)
                .collect(Collectors.toList());
    }

    public TripModel convertTripEntityToTripModel(TripEntity tripEntity) {

        TripModel tripModel = new TripModel();

        tripModel.setId(tripEntity.getId());
        tripModel.setCost(tripEntity.getCost());
        tripModel.setDuration(tripEntity.getDuration());

        RouteModel routeModel = routeConvertService.convertRouteEntityToRouteModel(tripEntity.getRoute());

        tripModel.setRoute(routeModel);

        return tripModel;
    }

    public TripEntity convertTripModelToTripEntity(TripModel tripModel) {

        TripEntity tripEntity = new TripEntity();

        tripEntity.setId(tripModel.getId());
        tripEntity.setCost(tripModel.getCost());
        tripEntity.setDuration(tripModel.getDuration());

        RouteEntity routeEntity = routeConvertService.convertRouteModelToRouteEntity(tripModel.getRoute());

        tripEntity.setRoute(routeEntity);

        return tripEntity;
    }
}

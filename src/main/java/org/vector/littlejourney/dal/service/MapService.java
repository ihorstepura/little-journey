package org.vector.littlejourney.dal.service;

import org.springframework.stereotype.Service;
import org.vector.littlejourney.api.dto.RouteModel;
import org.vector.littlejourney.api.dto.TripModel;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.api.dto.StationModel;
import org.vector.littlejourney.dal.dao.TripEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    private MapService() {
    }

    public static StationModel convertStationEntityToStationModel(StationEntity stationEntity) {

        StationModel stationModel = new StationModel();

        stationModel.setId(stationEntity.getId());

        stationModel.setName(stationEntity.getName());

        return stationModel;
    }

    public static List<StationModel> convertStationEntityToStationModel(List<StationEntity> stationEntities) {

        List<StationModel> stationModels = new ArrayList<>();

        for (StationEntity stationEntity : stationEntities) {

            stationModels.add(convertStationEntityToStationModel(stationEntity));
        }

        return stationModels;
    }

    public static StationEntity convertStationModelToStationEntity(StationModel stationModel) {

        StationEntity stationEntity = new StationEntity();

        stationEntity.setId(stationModel.getId());

        stationEntity.setName(stationModel.getName());

        return stationEntity;
    }

    public static RouteModel convertRouteEntityToRouteModel(RouteEntity routeEntity) {

        RouteModel routeModel = new RouteModel();

        StationModel departureStation = convertStationEntityToStationModel(routeEntity.getDepartureStation());

        StationModel arrivalStation = convertStationEntityToStationModel(routeEntity.getArrivalStation());

        routeModel.setId(routeEntity.getId());

        routeModel.setDepartureStation(departureStation);

        routeModel.setArrivalStation(arrivalStation);

        return routeModel;
    }

    public static List<RouteModel> convertRouteEntityToRouteModel(List<RouteEntity> routeEntities) {

        List<RouteModel> routeModels = new ArrayList<>();

        for (RouteEntity routeEntity : routeEntities) {

            routeModels.add(convertRouteEntityToRouteModel(routeEntity));
        }

        return routeModels;
    }

    public static RouteEntity convertRouteModelToRouteEntity(RouteModel routeModel) {

        RouteEntity routeEntity = new RouteEntity();

        StationEntity departureStation = convertStationModelToStationEntity(routeModel.getDepartureStation());

        StationEntity arrivalStation = convertStationModelToStationEntity(routeModel.getArrivalStation());

        routeEntity.setId(routeModel.getId());

        routeEntity.setDepartureStation(departureStation);

        routeEntity.setArrivalStation(arrivalStation);

        return routeEntity;
    }

    public static TripModel convertTripEntityToTripModel(TripEntity tripEntity) {

        TripModel tripModel = new TripModel();

        tripModel.setId(tripEntity.getId());

        tripModel.setCost(tripEntity.getCost());

        tripModel.setDuration(tripEntity.getDuration());

        RouteModel routeModel = convertRouteEntityToRouteModel(tripEntity.getRoute());

        tripModel.setRoute(routeModel);

        return tripModel;
    }

    public static List<TripModel> convertTripEntityToTripModel(List<TripEntity> tripEntities) {

        List<TripModel> tripModels = new ArrayList<>();

        for (TripEntity tripEntity : tripEntities) {

            tripModels.add(convertTripEntityToTripModel(tripEntity));
        }

        return tripModels;
    }

    public static TripEntity convertTripModelToTripEntity(TripModel tripModel) {

        TripEntity tripEntity = new TripEntity();

        tripEntity.setId(tripModel.getId());

        tripEntity.setCost(tripModel.getCost());

        tripEntity.setDuration(tripModel.getDuration());

        RouteEntity routeEntity = convertRouteModelToRouteEntity(tripModel.getRoute());

        tripEntity.setRoute(routeEntity);

        return tripEntity;
    }
}

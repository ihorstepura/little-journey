package org.vector.littlejourney.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.api.dto.RouteModel;
import org.vector.littlejourney.api.dto.StationModel;
import org.vector.littlejourney.dal.dao.RouteEntity;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.service.RouteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteConvertService {

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationConvertService stationConvertService;

    public RouteModel getById(Long id) {

        return convertRouteEntityToRouteModel(routeService.getById(id));
    }

    public List<RouteModel> getAll() {

        return routeService.getAll()
                .stream()
                .map(this::convertRouteEntityToRouteModel)
                .collect(Collectors.toList());
    }

    public RouteModel insert(RouteModel routeModel) {

        RouteEntity routeEntity = routeService.insert(convertRouteModelToRouteEntity(routeModel));

        routeModel.setId(routeEntity.getId());

        return routeModel;
    }

    public RouteModel update(RouteModel routeModel) {

        routeService.update(convertRouteModelToRouteEntity(routeModel));

        return routeModel;
    }

    public RouteModel delete(Long id) {

        RouteModel routeModel = convertRouteEntityToRouteModel(routeService.getById(id));

        routeService.delete(id);

        return routeModel;
    }

    public RouteModel convertRouteEntityToRouteModel(RouteEntity routeEntity) {

        RouteModel routeModel = new RouteModel();

        StationModel departureStation = stationConvertService
                .convertStationEntityToStationModel(routeEntity.getDepartureStation());
        StationModel arrivalStation = stationConvertService
                .convertStationEntityToStationModel(routeEntity.getArrivalStation());

        routeModel.setId(routeEntity.getId());
        routeModel.setDepartureStation(departureStation);
        routeModel.setArrivalStation(arrivalStation);

        return routeModel;
    }

    public RouteEntity convertRouteModelToRouteEntity(RouteModel routeModel) {

        RouteEntity routeEntity = new RouteEntity();

        StationEntity departureStation = stationConvertService
                .convertStationModelToStationEntity(routeModel.getDepartureStation());
        StationEntity arrivalStation = stationConvertService
                .convertStationModelToStationEntity(routeModel.getArrivalStation());

        routeEntity.setId(routeModel.getId());
        routeEntity.setDepartureStation(departureStation);
        routeEntity.setArrivalStation(arrivalStation);

        return routeEntity;
    }
}

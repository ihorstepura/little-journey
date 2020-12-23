package org.vector.littlejourney.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.littlejourney.api.dto.StationModel;
import org.vector.littlejourney.dal.dao.StationEntity;
import org.vector.littlejourney.dal.service.StationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationConvertService {

    @Autowired
    private StationService stationService;

    public StationModel getById(Long id) {

        return convertStationEntityToStationModel(stationService.getById(id));
    }

    public List<StationModel> getAll() {

        return stationService.getAll()
                .stream()
                .map(this::convertStationEntityToStationModel)
                .collect(Collectors.toList());
    }

    public StationModel insert(StationModel stationModel) {

        StationEntity stationEntity = stationService.insert(convertStationModelToStationEntity(stationModel));

        stationModel.setId(stationEntity.getId());

        return stationModel;
    }

    public StationModel update(StationModel stationModel) {

        stationService.update(convertStationModelToStationEntity(stationModel));

        return stationModel;
    }

    public StationModel delete(Long id) {

        StationModel stationModel = convertStationEntityToStationModel(stationService.getById(id));

        stationService.delete(id);

        return stationModel;
    }

    public List<StationModel> findStationsWithExampleMatcher(String stationName) {

        List<StationEntity> stationEntities = stationService.findStationsWithExampleMatcher(stationName);

        return stationEntities
                .stream()
                .map(this::convertStationEntityToStationModel)
                .collect(Collectors.toList());
    }

    public StationModel convertStationEntityToStationModel(StationEntity stationEntity) {

        StationModel stationModel = new StationModel();

        stationModel.setId(stationEntity.getId());
        stationModel.setName(stationEntity.getName());

        return stationModel;
    }

    public StationEntity convertStationModelToStationEntity(StationModel stationModel) {

        StationEntity stationEntity = new StationEntity();

        stationEntity.setId(stationModel.getId());
        stationEntity.setName(stationModel.getName());

        return stationEntity;
    }
}

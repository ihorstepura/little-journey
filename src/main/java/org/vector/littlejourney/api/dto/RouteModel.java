package org.vector.littlejourney.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RouteModel {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "departureStation")
    private StationModel departureStation;

    @JsonProperty(value = "arrivalStation")
    private StationModel arrivalStation;
}

package org.vector.littlejourney.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TripModel {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "cost")
    private Double cost;

    @JsonProperty(value = "duration")
    private String duration;

    @JsonProperty(value = "route")
    private RouteModel route;
}

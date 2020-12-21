package org.vector.littlejourney.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TripModel {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "cost")
    private Double cost;

    @JsonProperty(value = "duration")
    private Date duration;

    @JsonProperty(value = "route")
    private RouteModel route;
}

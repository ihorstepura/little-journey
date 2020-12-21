package org.vector.littlejourney.dal.dao;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "route")
public class RouteEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_station_id")
    private StationEntity departureStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_station_id")
    private StationEntity arrivalStation;
}

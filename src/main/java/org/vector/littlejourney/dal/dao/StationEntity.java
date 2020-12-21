package org.vector.littlejourney.dal.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "station")
public class StationEntity extends BaseEntity {

    @OneToMany(mappedBy = "departureStation")
    private List<RouteEntity> departureStations;

    @OneToMany(mappedBy = "arrivalStation")
    private List<RouteEntity> arrivalStations;

    @Column(name = "name")
    private String name;
}

package org.vector.littlejourney.dal.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "trip")
public class TripEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private RouteEntity route;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "duration")
    private String duration;
}

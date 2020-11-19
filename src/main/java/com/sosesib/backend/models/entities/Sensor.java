package com.sosesib.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="sensors")

public class Sensor {
    @Id
    @Column(name="sensor_id")
    private Integer sensor_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonProperty("location")
    private void createLocation(java.util.UUID location_id) {
        this.location = new Location();
        location.setLocation_id(location_id);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="measurement_id")
    private Measurement measurement;

    @JsonProperty("measurement")
    private void createMeasurement(java.util.UUID measurement_id) {
        this.measurement = new Measurement();
        measurement.setMeasurement_id(measurement_id);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="specification_id")
    private Specification specification;

    @JsonProperty("specification")
    private void createSpecification(java.util.UUID specification_id) {
        this.specification = new Specification();
        specification.setSpecification_id(specification_id);
    }

    @Column(name="description")
    private String description;
}

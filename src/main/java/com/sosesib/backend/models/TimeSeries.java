package com.sosesib.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="timeseries")
public class TimeSeries {
    @Id
    @Column(name="measurement_id")
    private UUID measurementId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sensor_id", insertable = false , updatable = false)
    private Sensor sensor;

    @Column(name="sensor_id")
    private Integer sensorId;

    @JsonProperty("sensor")
    private void createSensor(int sensor_id) {
        this.sensor = new Sensor();
        sensor.setSensor_id(sensor_id);
    }

    @Column(name="measurement_date")
    private LocalDateTime measurementDate;

    @Column(name="measurement_value",nullable=false)
    private Double measurementValue;
}

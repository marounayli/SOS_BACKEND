package com.sosesib.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name="sensor_id")
    private Integer sensorId;

    @Column(name="measurement_date")
    private LocalDateTime measurementDate;

    @Column(name="measurement_value",nullable=false)
    private Double measurementValue;
}

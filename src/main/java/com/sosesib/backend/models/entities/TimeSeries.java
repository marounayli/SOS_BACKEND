package com.sosesib.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="sensor_id", insertable = false , updatable = false)
    private Sensor sensor;

    @Column(name="sensor_id")
    private Integer sensorId;

    @Column(name="measurement_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH-mm-ss")
    private LocalDateTime measurementDate;

    @Column(name="measurement_value",nullable=false)
    private Double measurementValue;
}

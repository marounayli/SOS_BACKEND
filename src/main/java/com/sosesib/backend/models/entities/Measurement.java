package com.sosesib.backend.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.postgresql.geometric.PGpoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="measurement")

public class Measurement {
    @Id
    @Column(name="measurement_id")
    private UUID measurement_id;

    @Column(name="type")
    private String type;

    @Column(name="measurement_unit")
    private String measurement_unit;
}

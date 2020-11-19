package com.sosesib.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="specification")

public class Specification {
    @Id
    @Column(name="specification_id")
    private UUID specification_id;

    @Column(name="dimension")
    private String dimension;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="max_value")
    private String max_value;

    @Column(name="min_value")
    private String min_value;

    @Column(name="precision")
    private String precision;
}

package com.sosesib.backend.models;

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
@Table(name="location")
public class Location {
    @Id
    @Column(name="location_id")
    private UUID location_id;

    @Column(name="location")
    private PGpoint location;

    @Column(name="country")
    private String country;

    @Column(name="region")
    private String region;

    @Column(name="city")
    private String city;

    @Column(name="areacode")
    private String areacode;
}

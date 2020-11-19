package com.sosesib.backend.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
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
@TypeDef(name = "type", typeClass = PGPointType.class)
public class Location {
    @Id
    @Column(name="location_id")
    private UUID location_id;

    @Column(name="location")
    @Type(type = "type")
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

package com.sosesib.backend.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Humans")
@Getter
@Setter
@NoArgsConstructor
public class Human {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    @Column(name="age",nullable=false)
    private int age;

    public Human(String id , String firstName , String lastName , int age){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
    }

}

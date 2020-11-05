package com.sosesib.backend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Identification {
    private Person person;
    private School school;

    @Override
    public String toString() {
        if(school==null){
            return String.format("Hi my name is %s I am %s years old.",person.getName(),person.getAge());
        }
        return String.format("Hi my name is %s I am %s years old I go to %s school",person.getName(),person.getAge(),school.getName());
    }

    public Identification(String name , int age , String school){
        this.person= new Person(name,age);
        this.school=new School(school);
    }
}


@Getter
@Setter
class Person {
    private String name;
    private int age;

    public Person(String name , int age){
        this.age=age;
        this.name=name;
    }
}

@Getter
@Setter
class School {
    private String name;
    public School(String name){
        this.name=name;
    }
}



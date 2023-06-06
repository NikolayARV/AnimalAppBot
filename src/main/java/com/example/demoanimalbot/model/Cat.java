package com.example.demoanimalbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Cat extends Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int age;
    private String breed;
    private String description;
    private Status status;
    private LocalDate dateOfAdoption;
    @ManyToOne
    private UserCat user;

    public Cat(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.status = Status.SHELTER;
    }


}

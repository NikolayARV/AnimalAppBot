package com.example.demoanimalbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class CatReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String diet;
    private String behavior;
    private String wellBeing;
    private LocalDateTime sendDate;
    @OneToOne
    private Photo photo;
    @ManyToOne
    private Cat cat;

}

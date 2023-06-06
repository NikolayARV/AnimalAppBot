package com.example.demoanimalbot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

public class DogReport {
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
    private Dog dog;
}

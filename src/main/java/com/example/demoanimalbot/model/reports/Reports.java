package com.example.demoanimalbot.model.reports;

import com.example.demoanimalbot.model.pets.Dog;
import com.example.demoanimalbot.model.pets.Pet;
import com.pengrad.telegrambot.model.PhotoSize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
@EqualsAndHashCode
public abstract class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String diet;
    private String behavior;
    private String wellBeing;
    private LocalDateTime sendDate;

    @OneToOne
    private Photo photo;

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", diet='" + diet + '\'' +
                ", behavior='" + behavior + '\'' +
                ", wellBeing='" + wellBeing + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }

    public Reports(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}

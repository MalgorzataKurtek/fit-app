package com.example.fitApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Collection;

@Entity
@Table(name = "exercise")
@Setter
@Getter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany
    @JoinColumn(name="id_exercise")
    private Collection<SerieSet> series;

    private Duration duration;
    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    //TYP WYLICZENIOWY, wartości typu wyliczeniowego zostana zapisane w bazie danych jako łańcuchy znaków
    // (np. "CARDIO", "STRENGTH", "STRETCHING"), co może ułatwić odczytanie danych bezpośrednio z bazy
    public enum ExerciseType {
        CARDIO, STRENGTH, STRETCHING
    }
}

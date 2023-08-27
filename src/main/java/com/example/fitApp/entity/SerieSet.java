package com.example.fitApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "serie")
@Setter
@Getter
public class SerieSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private int countOfSerie;

    @ManyToOne
    private Exercise exercise;

}
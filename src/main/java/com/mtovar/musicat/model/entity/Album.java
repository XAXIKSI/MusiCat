package com.mtovar.musicat.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int year;
}

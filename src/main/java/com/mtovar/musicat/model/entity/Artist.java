package com.mtovar.musicat.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "artist_instrument",
//            joinColumns = @JoinColumn(name = "artist_id"),
//            inverseJoinColumns = @JoinColumn(name = "instrument_id")
//    )
//    private Set<Instrument> instruments = new HashSet<>();
}

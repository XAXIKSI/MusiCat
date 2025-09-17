package com.mtovar.musicat.model.entity;

import com.mtovar.musicat.config.Constans;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private LocalDate recordedDate = Constans.MIN_DATE;

    @ManyToMany(mappedBy = "tracks")
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrackAlbum> trackAlbums = new HashSet<>();
}

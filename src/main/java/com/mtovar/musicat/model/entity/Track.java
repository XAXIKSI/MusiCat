package com.mtovar.musicat.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtovar.musicat.model.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private LocalDate recordedDate = Constants.MIN_DATE;

    @JsonIgnore
    @ManyToMany(mappedBy = "tracks")
    private Set<Artist> artists = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrackAlbum> trackAlbums = new HashSet<>();
}

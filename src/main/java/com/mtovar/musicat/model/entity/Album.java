package com.mtovar.musicat.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtovar.musicat.config.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "albums")
public class Album {
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
    private LocalDate releasedDate = Constants.MIN_DATE;

    @JsonIgnore
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrackAlbum> trackAlbums = new HashSet<>();
}

package com.mtovar.musicat.model.entity;

import com.mtovar.musicat.config.Constans;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "track_album")
public class TrackAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Track track;

    private Integer discNumber = Constans.DEFAULT_TAKE_AND_DISC_NUMBER;

    private Integer trackNumber = Constans.DEFAULT_TAKE_AND_DISC_NUMBER;
}


package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.TrackAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackAlbumRepository extends JpaRepository<TrackAlbum, Long> {
}

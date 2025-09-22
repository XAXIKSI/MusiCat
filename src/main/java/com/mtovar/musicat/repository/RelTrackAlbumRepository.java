package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.RelTrackAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelTrackAlbumRepository extends JpaRepository<RelTrackAlbum, Long> {
}

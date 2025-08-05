package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    boolean existsByTitle(String title);
}

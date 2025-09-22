package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    boolean existsByTitle(String title);
    boolean existsByTitleAndOwnerAndRecordedDate(String title, String owner, LocalDate recordedDate);
}

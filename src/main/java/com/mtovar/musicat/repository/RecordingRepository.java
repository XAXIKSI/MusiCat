package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<Recording, Long> {
    boolean existsByTitle(String title);
}

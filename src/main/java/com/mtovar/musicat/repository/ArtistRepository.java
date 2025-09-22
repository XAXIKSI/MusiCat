package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByName(String name);
}

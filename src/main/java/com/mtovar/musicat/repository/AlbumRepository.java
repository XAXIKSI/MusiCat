package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsByTitle(String title);
}

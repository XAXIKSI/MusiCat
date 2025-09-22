package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsByTitle(String title);
    boolean existsByTitleAndOwnerAndReleasedDate(String title, String owner, LocalDate releasedDate);
}

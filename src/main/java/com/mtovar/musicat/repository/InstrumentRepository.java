package com.mtovar.musicat.repository;

import com.mtovar.musicat.model.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    boolean existsByName(String name);
}

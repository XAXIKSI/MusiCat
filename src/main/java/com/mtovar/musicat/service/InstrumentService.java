package com.mtovar.musicat.service;

import com.mtovar.musicat.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mtovar.musicat.model.entity.Instrument;

import java.util.List;

@Service
@Transactional
public class InstrumentService {
    private final InstrumentRepository repository;

    @Autowired
    public InstrumentService(InstrumentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public Instrument create(Instrument instrument) {
        if (instrument.getId() != null) {
            throw new IllegalArgumentException("Instrument ID must be null for a new instrument");
        }
        if (instrument.getName() == null || instrument.getName().isEmpty()) {
            throw new IllegalArgumentException("Instrument name cannot be null or empty");
        }
        if (repository.existsByName(instrument.getName())) {
            throw new IllegalArgumentException("Instrument with name '" + instrument.getName() + "' already exists");
        }
        return repository.save(instrument);
    }

    @Transactional(readOnly = true)
    public List<Instrument> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Instrument findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found with id: " + id));
    }

    public Instrument update(Long id, Instrument instrument) {
        Instrument existingInstrument = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found with id: " + id));

        if (instrument.getName() == null || instrument.getName().isEmpty()) {
            throw new IllegalArgumentException("Instrument name cannot be null or empty");
        }
        if (!existingInstrument.getName().equals(instrument.getName()) && repository.existsByName(instrument.getName())) {
            throw new IllegalArgumentException("Instrument with name '" + instrument.getName() + "' already exists");
        }

        existingInstrument.setName(instrument.getName());
        return repository.save(existingInstrument);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Instrument not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
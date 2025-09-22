package com.mtovar.musicat.service;

import com.mtovar.musicat.model.entity.Artist;
import com.mtovar.musicat.exception.custom.ResourceNotFoundException;
import com.mtovar.musicat.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtistService {

    private final ArtistRepository repository;

    @Autowired
    public ArtistService(ArtistRepository repository) {
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

    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Artist findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));
    }

    public Artist create(Artist artist) {
        if (artist.getId() != null) {
            throw new IllegalArgumentException("Artist ID must be null for a new artist");
        }
        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        // No sé si dejar esta condición porque hay, por ejemplo, dos Bill Evans
        if (repository.existsByName(artist.getName())) {
            throw new IllegalArgumentException("Artist with name '" + artist.getName() + "' already exists");
        }
        return repository.save(artist);
    }

    public Artist update(Long id, Artist artist) {
        Artist existingArtist = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));

        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        existingArtist.setName(artist.getName());
        return repository.save(existingArtist);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Artist not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
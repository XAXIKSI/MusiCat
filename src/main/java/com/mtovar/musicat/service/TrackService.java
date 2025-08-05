package com.mtovar.musicat.service;

import com.mtovar.musicat.model.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import com.mtovar.musicat.exception.custom.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.mtovar.musicat.repository.TrackRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrackService {
    private final TrackRepository repository;

    @Autowired
    public TrackService(TrackRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByTitle(String trackTitle) {
        return repository.existsByTitle(trackTitle);
    }

    public Track create(Track track) {
        if (track.getId() != null) {
            throw new IllegalArgumentException("Track ID must be null for a new track");
        }
        if (track.getTitle() == null || track.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Track name cannot be null or empty");
        }
        if (repository.existsByTitle(track.getTitle())) {
            throw new IllegalArgumentException("Track with name '" + track.getTitle() + "' already exists");
        }
        return repository.save(track);
    }

    @Transactional(readOnly = true)
    public List<Track> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Track findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found with id: " + id));
    }

    public Track update(Long id, Track track) {
        Track existingTrack = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found with id: " + id));

        if (track.getTitle() == null || track.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Track title cannot be null or empty");
        }
        if (!existingTrack.getTitle().equals(track.getTitle()) && repository.existsByTitle(track.getTitle())) {
            throw new IllegalArgumentException("Track with name '" + track.getTitle() + "' already exists");
        }

        existingTrack.setTitle(track.getTitle());
        return repository.save(track);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Track not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

package com.mtovar.musicat.service;

import com.mtovar.musicat.model.entity.Artist;
import com.mtovar.musicat.repository.ArtistRepository;
import com.mtovar.musicat.exception.custom.ResourceNotFoundException;
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

    public void init() {
        if (repository.count() == 0) {
            List<String> artistNames = List.of(
                    // Iron Maiden
                    "Paul Di'Anno", "Bruce Dickinson", "Dennis Stratton",
                    "Adrian Smith", "Janick Gers", "Clive Burr",
                    "Nicko McBrain", "Dave Murray", "Steve Harris",
                    // Led Zeppelin
                    "Jimmy Page", "Robert Plant", "John Paul Jones", "John Bonham"
            );

            List<Artist> artists = artistNames.stream()
                    .map(name -> {
                        Artist artist = new Artist();
                        artist.setName(name);
                        return artist;
                    })
                    .toList();

            repository.saveAll(artists);

//        Ejemplo 1
//        artist = artistRepository.findById(artistId);
//        instrument = instrumentRepository.findById(instrumentId);
//        artist.getInstruments().add(instrument);
//        instrument.getArtists().add(artist);
//        artistRepository.save(artist);

//        Ejemplo 2
//        Artist firstArtist = artistService.findById(1L);
//        Instrument firstInstrument = instrumentService.findById(1L);
//        firstArtist.setInstruments(Set.of(firstInstrument));
//        artistService.update(firstArtist.getId(), firstArtist);
        }
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public Artist create(Artist artist) {
        if (artist.getId() != null) {
            throw new IllegalArgumentException("Artist ID must be null for a new artist");
        }
        if (artist.getName() == null || artist.getName().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        if (repository.existsByName(artist.getName())) {
            throw new IllegalArgumentException("Artist with name '" + artist.getName() + "' already exists");
        }
        return repository.save(artist);
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

    public Artist update(Long id, Artist artist) {
        Artist existingArtist = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));

        if (artist.getName() == null || artist.getName().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        if (!existingArtist.getName().equals(artist.getName()) && repository.existsByName(artist.getName())) {
            throw new IllegalArgumentException("Artist with name '" + artist.getName() + "' already exists");
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
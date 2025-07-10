package com.mtovar.musicat.service;

import com.mtovar.musicat.model.entity.Artist;
import com.mtovar.musicat.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void init() {
        if (artistRepository.count() == 0) {
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

            artistRepository.saveAll(artists);

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

    /**
     * Checks if an artist exists by its ID.
     *
     * @param id the ID of the artist
     * @return true if the artist exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return artistRepository.existsById(id);
    }

    /**
     * Checks if an artist exists by its name.
     *
     * @param name the name of the artist
     * @return true if the artist exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return artistRepository.existsByName(name);
    }

    /**
     * Creates a new artist.
     *
     * @param artist the artist to create
     * @return the created artist
     * @throws IllegalArgumentException if the artist ID is not null, or if the name is null or empty, or if an artist with the same name already exists
     */
    public Artist create(Artist artist) {
        if (artist.getId() != null) {
            throw new IllegalArgumentException("Artist ID must be null for a new artist");
        }
        if (artist.getName() == null || artist.getName().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        if (artistRepository.existsByName(artist.getName())) {
            throw new IllegalArgumentException("Artist with name '" + artist.getName() + "' already exists");
        }
        return artistRepository.save(artist);
    }

    /**
     * Retrieves all artists sorted by name.
     *
     * @return a list of all artists sorted by name
     */
    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    /**
     * Retrieves all artists with pagination.
     *
     * @param pageable the pagination information
     * @return a page of artists
     */
    @Transactional(readOnly = true)
    public Page<Artist> findAll(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    /**
     * Retrieves an artist by its ID.
     *
     * @param id the ID of the artist
     * @return an Optional containing the artist if found, or empty if not found
     */
    @Transactional(readOnly = true)
    public Artist findById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));
    }

    /**
     * Updates an existing artist.
     *
     * @param id the ID of the artist to update
     * @param artist the artist data to update
     * @return the updated artist
     * @throws ResourceNotFoundException if the artist with the given ID does not exist
     * @throws IllegalArgumentException if the artist name is null or empty, or if an artist with the same name already exists
     */
    public Artist update(Long id, Artist artist) {
        Artist existingArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));

        if (artist.getName() == null || artist.getName().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty");
        }
        if (!existingArtist.getName().equals(artist.getName()) && artistRepository.existsByName(artist.getName())) {
            throw new IllegalArgumentException("Artist with name '" + artist.getName() + "' already exists");
        }

        existingArtist.setName(artist.getName());
        return artistRepository.save(existingArtist);
    }

    /**
     * Deletes an artist by its ID.
     *
     * @param id the ID of the artist to delete
     */
    public void delete(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artist not found with id: " + id);
        }
        artistRepository.deleteById(id);
    }
}
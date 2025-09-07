package com.mtovar.musicat.service;

import com.mtovar.musicat.model.entity.Album;
import com.mtovar.musicat.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mtovar.musicat.exception.custom.ResourceNotFoundException;
import java.time.Year;

import java.util.List;

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository repository;

    private final int firstRecordedYear = 1857;

    @Autowired
    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    public void init() {
        if (repository.count() == 0) {
            // Initialize with some sample albums
            List<Object[]> albumData = List.of(
                    // Iron Maiden
                    new Object[]{"Iron Maiden", 1980},
                    new Object[]{"Killers", 1981},
                    new Object[]{"The Number of the Beast", 1982},
                    new Object[]{"Piece of Mind", 1983},
                    new Object[]{"Powerslave", 1984},
                    new Object[]{"Somewhere in Time", 1986},

                    // Led Zeppelin
                    new Object[]{"Led Zeppelin", 1969},
                    new Object[]{"Led Zeppelin II", 1969},
                    new Object[]{"Led Zeppelin III", 1970},
                    new Object[]{"Led Zeppelin IV", 1971},
                    new Object[]{"Houses of the Holy", 1973},
                    new Object[]{"Physical Graffiti", 1975},
                    new Object[]{"Presence", 1976},
                    new Object[]{"In Through the Out Door", 1979},
                    new Object[]{"Coda", 1982}
            );

            List<Album> albums = albumData.stream()
                    .map(data -> {
                        Album album = new Album();
                        album.setTitle((String) data[0]);
                        album.setYear((Integer) data[1]);
                        return album;
                    })
                    .toList();

            repository.saveAll(albums);
        }
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByTitle(String albumTitle){
        return repository.existsByTitle(albumTitle);
    }

    public Album create(Album album) {
        if (album.getId() != null) {
            throw new IllegalArgumentException("Album ID must be null for a new album");
        }
        if (album.getTitle() == null || album.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Album title cannot be null or empty");
        }
        if (album.getYear() < firstRecordedYear || album.getYear() > Year.now().getValue()) {
            throw new IllegalArgumentException("Album year must be between " + firstRecordedYear + " and the current year");
        }
        if (repository.existsByTitle(album.getTitle())) {
            throw new IllegalArgumentException("Album with title '" + album.getTitle() + "' already exists");
        }
        return repository.save(album);
    }

    @Transactional(readOnly = true)
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Album findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found with id: " + id));
    }

    public Album update(Long id, Album album) {
        Album existingAlbum = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album not found with id: " + id));

        if (album.getTitle() == null || album.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Album title cannot be null or empty");
        }
        if (album.getYear() < firstRecordedYear || album.getYear() > Year.now().getValue()) {
            throw new IllegalArgumentException("Album year must be between " + firstRecordedYear + " and the current year");
        }
        if (!existingAlbum.getTitle().equals(album.getTitle()) && repository.existsByTitle(album.getTitle())) {
            throw new IllegalArgumentException("Album with name '" + album.getTitle() + "' already exists");
        }

        existingAlbum.setTitle(album.getTitle());
        existingAlbum.setYear(album.getYear());
        return repository.save(album);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Album not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

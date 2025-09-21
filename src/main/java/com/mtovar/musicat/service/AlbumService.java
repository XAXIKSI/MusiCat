package com.mtovar.musicat.service;

import com.mtovar.musicat.model.Constants;
import com.mtovar.musicat.model.entity.Album;
import com.mtovar.musicat.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mtovar.musicat.exception.custom.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository repository;

    @Autowired
    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
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
            throw new IllegalArgumentException("Album ID must be null for a new track");
        }
        if (album.getTitle() == null || album.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Album name cannot be null or empty");
        }
        if (album.getOwner() == null || album.getOwner().trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        if (album.getReleasedDate().isBefore(Constants.MIN_DATE)  || album.getReleasedDate().isAfter(Constants.MAX_DATE)) {
            throw new IllegalArgumentException("Album date must be between " + Constants.MIN_DATE + " and the current date");
        }
        if (repository.existsByTitleAndOwnerAndReleasedDate(album.getTitle(), album.getOwner(), album.getReleasedDate())) {
            throw new IllegalArgumentException("Album with name '" + album.getTitle() + "' already exists");
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

        if (album.getTitle() == null || album.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Album name cannot be null or empty");
        }
        if (album.getOwner() == null || album.getOwner().trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        if (album.getReleasedDate().isBefore(Constants.MIN_DATE)  || album.getReleasedDate().isAfter(Constants.MAX_DATE)) {
            throw new IllegalArgumentException("Album date must be between " + Constants.MIN_DATE + " and the current date");
        }

        existingAlbum.setTitle(album.getTitle());
        existingAlbum.setOwner(album.getOwner());
        existingAlbum.setReleasedDate(album.getReleasedDate());
        return repository.save(album);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Album not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

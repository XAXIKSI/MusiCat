package com.mtovar.musicat.controller;

import com.mtovar.musicat.service.ArtistService;
import com.mtovar.musicat.model.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService service;

    @Autowired
    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Artist> create(@RequestBody Artist artist) {
        Artist created = service.create(artist);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Artist> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getById(@PathVariable Long id) {
        Artist artist = service.findById(id);
        return ResponseEntity.ok(artist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> update(@PathVariable Long id, @RequestBody Artist artist) {
        Artist updated = service.update(id, artist);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

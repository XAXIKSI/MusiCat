package com.mtovar.musicat.controller;

import com.mtovar.musicat.model.entity.Track;
import com.mtovar.musicat.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    private final TrackService service;

    @Autowired
    public TrackController(TrackService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Track> create(@RequestBody Track track) {
        Track created = service.create(track);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Track> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getById(@PathVariable Long id) {
        Track track = service.findById(id);
        return ResponseEntity.ok(track);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Track> update(@PathVariable Long id, @RequestBody Track track) {
        Track updated = service.update(id, track);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

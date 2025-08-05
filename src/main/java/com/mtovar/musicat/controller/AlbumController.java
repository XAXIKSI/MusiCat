package com.mtovar.musicat.controller;

import com.mtovar.musicat.model.entity.Album;
import com.mtovar.musicat.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService service;

    @Autowired
    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Album> create(@RequestBody Album album) {
        Album created = service.create(album);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Album> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getById(@PathVariable Long id) {
        Album album = service.findById(id);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@PathVariable Long id, @RequestBody Album album) {
        Album updated = service.update(id, album);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package com.mtovar.musicat.controller;

import com.mtovar.musicat.model.entity.Album;
import com.mtovar.musicat.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album created = albumService.create(album);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album updated = albumService.update(id, album);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

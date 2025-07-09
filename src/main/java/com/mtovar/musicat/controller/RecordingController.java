package com.mtovar.musicat.controller;

import com.mtovar.musicat.model.entity.Recording;
import com.mtovar.musicat.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordings")
public class RecordingController {
    private final RecordingService recordingService;

    @Autowired
    public RecordingController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @PostMapping
    public ResponseEntity<Recording> createRecording(@RequestBody Recording recording) {
        Recording created = recordingService.create(recording);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Recording> getAllRecordings() {
        return recordingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recording> getRecordingById(@PathVariable Long id) {
        Recording recording = recordingService.findById(id);
        return ResponseEntity.ok(recording);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recording> updateRecording(@PathVariable Long id, @RequestBody Recording recording) {
        Recording updated = recordingService.update(id, recording);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecording(@PathVariable Long id) {
        recordingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

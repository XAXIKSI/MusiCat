package com.mtovar.musicat.service;

import com.mtovar.musicat.model.entity.Artist;
import com.mtovar.musicat.model.entity.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.mtovar.musicat.repository.RecordingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordingService {
    private final RecordingRepository recordingRepository;

    @Autowired
    public RecordingService(RecordingRepository recordingRepository) {
        this.recordingRepository = recordingRepository;
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return recordingRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByTitle(String recordingTitle) {
        return recordingRepository.existsByTitle(recordingTitle);
    }

    public Recording create(Recording recording) {
        if (recording.getId() != null) {
            throw new IllegalArgumentException("Recording ID must be null for a new recording");
        }
        if (recording.getTitle() == null || recording.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Recording name cannot be null or empty");
        }
        if (recording.getDate() == null) {
            throw new IllegalArgumentException("Recording date cannot be null");
        }
        if (recordingRepository.existsByTitle(recording.getTitle())) {
            throw new IllegalArgumentException("Recording with name '" + recording.getTitle() + "' already exists");
        }
        return recordingRepository.save(recording);
    }

    @Transactional(readOnly = true)
    public List<Recording> findAll() {
        return recordingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Recording findById(Long id) {
        return recordingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recording not found with id: " + id));
    }

    public Recording update(Long id, Recording recording) {
        Recording existingRecording = recordingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recording not found with id: " + id));

        if (recording.getTitle() == null || recording.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Recording title cannot be null or empty");
        }
        if (recording.getDate() == null) {
            throw new IllegalArgumentException("Recording date cannot be null");
        }
        if (!existingRecording.getTitle().equals(recording.getTitle()) && recordingRepository.existsByTitle(recording.getTitle())) {
            throw new IllegalArgumentException("Recording with name '" + recording.getTitle() + "' already exists");
        }

        existingRecording.setTitle(recording.getTitle());
        existingRecording.setDate(recording.getDate());
        return recordingRepository.save(recording);
    }

    public void delete(Long id) {
        Recording recording = recordingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recording not found with id: " + id));
        recordingRepository.deleteById(id);
    }
}

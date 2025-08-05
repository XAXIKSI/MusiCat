package com.mtovar.musicat.controller;

import com.mtovar.musicat.model.entity.Instrument;
import com.mtovar.musicat.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {
    private final InstrumentService service;

    @Autowired
    public InstrumentController(InstrumentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Instrument> create(@RequestBody Instrument instrument) {
        Instrument created = service.create(instrument);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Instrument> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getById(@PathVariable Long id) {
        Instrument instrument = service.findById(id);
        return ResponseEntity.ok(instrument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrument> update(@PathVariable Long id, @RequestBody Instrument instrument) {
        Instrument updated = service.update(id, instrument);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

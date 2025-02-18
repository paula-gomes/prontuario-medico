package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.ProntuarioDto;
import com.prontuarioMedico.service.ProntuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<ProntuarioDto> getAllProntuarios() {
        return prontuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioDto> getProntuarioById(@PathVariable Long id) {
        return prontuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProntuarioDto> createProntuario(@RequestBody @Valid ProntuarioDto prontuarioDto) {
        ProntuarioDto savedProntuario = prontuarioService.save(prontuarioDto);
        return ResponseEntity.ok(savedProntuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioDto> updateProntuario(
            @PathVariable Long id,
            @RequestBody @Valid ProntuarioDto prontuarioDto) {
        return prontuarioService.update(id, prontuarioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuario(@PathVariable Long id) {
        if (prontuarioService.findById(id).isPresent()) {
            prontuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
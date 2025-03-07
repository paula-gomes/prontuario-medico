package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.PrescricaoDto;
import com.prontuarioMedico.service.PrescricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoController {

    @Autowired
    private PrescricaoService prescricaoService;

    @GetMapping
    public List<PrescricaoDto> getAllPrescricoes() {
        return prescricaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescricaoDto> getPrescricaoById(@PathVariable Long id) {
        return prescricaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PrescricaoDto> createPrescricao(@RequestBody @Valid PrescricaoDto prescricaoDto) {
        PrescricaoDto savedPrescricao = prescricaoService.save(prescricaoDto);
        return ResponseEntity.ok(savedPrescricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescricaoDto> updatePrescricao(
            @PathVariable Long id,
            @RequestBody @Valid PrescricaoDto prescricaoDto) {
        return prescricaoService.update(id, prescricaoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescricao(@PathVariable Long id) {
        if (prescricaoService.findById(id).isPresent()) {
            prescricaoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
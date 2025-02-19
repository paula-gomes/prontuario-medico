package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.DiagnosticoDto;

import com.prontuarioMedico.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public List<DiagnosticoDto> getAllDiagnosticos() {
        return diagnosticoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoDto> getDiagnosticoById(@PathVariable Long id) {
      return diagnosticoService.findById(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiagnosticoDto> createDiagnostico(@RequestBody DiagnosticoDto diagnosticodto) {
        DiagnosticoDto savedDiagnostico = diagnosticoService.save(diagnosticodto);
        return ResponseEntity.ok(savedDiagnostico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoDto> updateDiagnostico(
            @PathVariable Long id,
            @RequestBody DiagnosticoDto diagnosticoDto) {
        return diagnosticoService.update(id, diagnosticoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnostico(@PathVariable Long id) {
        if (diagnosticoService.findById(id).isPresent()) {
            diagnosticoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
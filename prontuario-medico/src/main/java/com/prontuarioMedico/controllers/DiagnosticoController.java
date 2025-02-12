package com.prontuarioMedico.controllers;

import com.prontuarioMedico.entities.Diagnostico;

import com.prontuarioMedico.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> getDiagnosticoById(@PathVariable Long id) {
        Optional<Diagnostico> diagnostico = diagnosticoService.findById(id);
        return diagnostico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diagnostico createDiagnostico(@RequestBody Diagnostico diagnostico) {
        return diagnosticoService.save(diagnostico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> updateDiagnostico(@PathVariable Long id, @RequestBody Diagnostico diagnosticoDetails) {
        Optional<Diagnostico> diagnostico = diagnosticoService.findById(id);
        if (diagnostico.isPresent()) {
            Diagnostico updatedDiagnostico = diagnostico.get();
            updatedDiagnostico.setConsulta(diagnosticoDetails.getConsulta());
            updatedDiagnostico.setDescricao(diagnosticoDetails.getDescricao());
            return ResponseEntity.ok(diagnosticoService.save(updatedDiagnostico));
        } else {
            return ResponseEntity.notFound().build();
        }
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
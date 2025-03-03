package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<PacienteDto> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PacienteDto> createPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        PacienteDto savedPaciente = pacienteService.salvarPaciente(pacienteDto);
        return ResponseEntity.ok(savedPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(
            @PathVariable Long id,
            @RequestBody @Valid PacienteDto pacienteDto) {
        return pacienteService.updatePaciente(id, pacienteDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

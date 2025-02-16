package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.entities.Paciente;
import com.prontuarioMedico.mapper.PacienteMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private com.prontuarioMedico.service.PacienteService pacienteService;

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
    public PacienteDto createPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        Paciente paciente = PacienteMapper.toEntity(pacienteDto);

        if (paciente.getProntuario() != null) {
            paciente.setProntuario(paciente.getProntuario());
        }

        Paciente savedPaciente = pacienteService.save(paciente);
        return PacienteMapper.toDto(savedPaciente);
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(
            @PathVariable Long id,
            @RequestBody @Valid PacienteDto pacienteDto) {
        return pacienteService.updatePaciente(id, pacienteDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

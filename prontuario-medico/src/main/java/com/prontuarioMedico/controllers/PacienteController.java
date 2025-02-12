package com.prontuarioMedico.controllers;

import com.prontuarioMedico.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private com.prontuarioMedico.services.PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails) {
        Optional<Paciente> paciente = pacienteService.findById(id);
        if (paciente.isPresent()) {
            Paciente updatedPaciente = paciente.get();
            updatedPaciente.setNome(pacienteDetails.getNome());
            updatedPaciente.setCpf(pacienteDetails.getCpf());
            updatedPaciente.setDataNascimento(pacienteDetails.getDataNascimento());
            updatedPaciente.setEndereco(pacienteDetails.getEndereco());
            updatedPaciente.setTelefone(pacienteDetails.getTelefone());
            updatedPaciente.setProntuario(pacienteDetails.getProntuario());
            return ResponseEntity.ok(pacienteService.save(updatedPaciente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (pacienteService.findById(id).isPresent()) {
            pacienteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

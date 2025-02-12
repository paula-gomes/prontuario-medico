package com.prontuarioMedico.controllers;

import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Long id) {
        Optional<Consulta> consulta = consultaService.findById(id);
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Consulta createConsulta(@RequestBody Consulta consulta) {
        return consultaService.save(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @RequestBody Consulta consultaDetails) {
        Optional<Consulta> consulta = consultaService.findById(id);
        if (consulta.isPresent()) {
            Consulta updatedConsulta = consulta.get();
            updatedConsulta.setPaciente(consultaDetails.getPaciente());
            updatedConsulta.setDataConsulta(consultaDetails.getDataConsulta());
            updatedConsulta.setDiagnosticos(consultaDetails.getDiagnosticos());
            updatedConsulta.setExames(consultaDetails.getExames());
            updatedConsulta.setPrescricoes(consultaDetails.getPrescricoes());
            return ResponseEntity.ok(consultaService.save(updatedConsulta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        if (consultaService.findById(id).isPresent()) {
            consultaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
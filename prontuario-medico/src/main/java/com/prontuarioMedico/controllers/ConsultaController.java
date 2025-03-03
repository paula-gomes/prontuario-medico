package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.ConsultaDto;
import com.prontuarioMedico.service.ArmazenamentoService;
import com.prontuarioMedico.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ArmazenamentoService armazenamentoService;

    @GetMapping
    public List<ConsultaDto> getAllConsultas() {
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> getConsultaById(@PathVariable Long id) {
        return consultaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsultaDto> createConsulta(@RequestBody @Valid ConsultaDto consultaDto) {
        System.out.println("Recebendo consulta: " + consultaDto.getDataConsulta());
        ConsultaDto savedConsulta = consultaService.save(consultaDto);
        return ResponseEntity.ok(savedConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> updateConsulta(@PathVariable Long id,
                                                      @RequestBody @Valid ConsultaDto consultaDto) {
        return consultaService.update(id, consultaDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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

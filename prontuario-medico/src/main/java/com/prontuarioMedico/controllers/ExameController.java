package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.ExameDto;
import com.prontuarioMedico.service.ExameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @GetMapping
    public List<ExameDto> getAllExames() {
        return exameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameDto> getExameById(@PathVariable Long id) {
        return exameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ExameDto> createExame(@RequestBody @Valid ExameDto exameDto) {
        ExameDto savedExame = exameService.save(exameDto);
        return ResponseEntity.ok(savedExame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExameDto> updateExame(
            @PathVariable Long id,
            @RequestBody @Valid ExameDto exameDto) {
        return exameService.update(id, exameDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExame(@PathVariable Long id) {
        if (exameService.findById(id).isPresent()) {
            exameService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
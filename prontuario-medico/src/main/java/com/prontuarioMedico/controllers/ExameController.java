package com.prontuarioMedico.controllers;

import com.prontuarioMedico.entities.Exame;
import com.prontuarioMedico.services.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @GetMapping
    public List<Exame> getAllExames() {
        return exameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exame> getExameById(@PathVariable Long id) {
        Optional<Exame> exame = exameService.findById(id);
        return exame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exame createExame(@RequestBody Exame exame) {
        return exameService.save(exame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exame> updateExame(@PathVariable Long id, @RequestBody Exame exameDetails) {
        Optional<Exame> exame = exameService.findById(id);
        if (exame.isPresent()) {
            Exame updatedExame = exame.get();
            updatedExame.setConsulta(exameDetails.getConsulta());
            updatedExame.setTipo(exameDetails.getTipo());
            updatedExame.setDataExame(exameDetails.getDataExame());
            return ResponseEntity.ok(exameService.save(updatedExame));
        } else {
            return ResponseEntity.notFound().build();
        }
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
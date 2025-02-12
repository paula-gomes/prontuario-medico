package com.prontuarioMedico.controllers;

import com.prontuarioMedico.entities.Prescricao;
import com.prontuarioMedico.services.PrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoController {

    @Autowired
    private PrescricaoService prescricaoService;

    @GetMapping
    public List<Prescricao> getAllPrescricoes() {
        return prescricaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescricao> getPrescricaoById(@PathVariable Long id) {
        Optional<Prescricao> prescricao = prescricaoService.findById(id);
        return prescricao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prescricao createPrescricao(@RequestBody Prescricao prescricao) {
        return prescricaoService.save(prescricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescricao> updatePrescricao(@PathVariable Long id, @RequestBody Prescricao prescricaoDetails) {
        Optional<Prescricao> prescricao = prescricaoService.findById(id);
        if (prescricao.isPresent()) {
            Prescricao updatedPrescricao = prescricao.get();
            updatedPrescricao.setConsulta(prescricaoDetails.getConsulta());
            updatedPrescricao.setMedicamento(prescricaoDetails.getMedicamento());
            updatedPrescricao.setDosagem(prescricaoDetails.getDosagem());
            updatedPrescricao.setDataPrescricao(prescricaoDetails.getDataPrescricao());
            return ResponseEntity.ok(prescricaoService.save(updatedPrescricao));
        } else {
            return ResponseEntity.notFound().build();
        }
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
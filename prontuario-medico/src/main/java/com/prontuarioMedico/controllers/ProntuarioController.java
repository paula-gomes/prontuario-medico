package com.prontuarioMedico.controllers;

import com.prontuarioMedico.entities.Prontuario;
import com.prontuarioMedico.services.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<Prontuario> getAllProntuarios() {
        return prontuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> getProntuarioById(@PathVariable Long id) {
        Optional<Prontuario> prontuario = prontuarioService.findById(id);
        return prontuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prontuario createProntuario(@RequestBody Prontuario prontuario) {
        return prontuarioService.save(prontuario);
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> updateProntuario(@PathVariable Long id, @RequestBody Prontuario prontuarioDetails) {
        Optional<Prontuario> prontuarioOpt = prontuarioService.findById(id);
        if (prontuarioOpt.isPresent()) {
            Prontuario prontuario = prontuarioOpt.get();
            ProntuarioAccess access = new ProntuarioAccess(prontuario);
            ProntuarioAccess details = new ProntuarioAccess(prontuarioDetails);
            access.setPaciente(details.getPaciente());
            access.setDataCriacao(details.getDataCriacao());
            access.setConsultas(details.getConsultas());
            return ResponseEntity.ok(prontuarioService.save(prontuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuario(@PathVariable Long id) {
        if (prontuarioService.findById(id).isPresent()) {
            prontuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
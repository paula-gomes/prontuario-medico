package com.prontuarioMedico.controllers;

import com.prontuarioMedico.access.ConsultaAccess;
import com.prontuarioMedico.entities.Consulta;
import com.prontuarioMedico.service.ArmazenamentoService;
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

    @Autowired
    private ArmazenamentoService armazenamentoService;
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
        // Faz o upload da imagem e obtém a URL
        //        String imagemUrl = armazenamentoService.uploadFile(imagem);
        //        consulta.setImageUrl(imagemUrl);
        //  Criar resto do objeto
        return consultaService.save(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @RequestBody Consulta consultaDetails) {
        Optional<Consulta> consultaOpt = consultaService.findById(id);
        if (consultaOpt.isPresent()) {
            Consulta consulta = consultaOpt.get();
            ConsultaAccess access = new ConsultaAccess(consulta);
            ConsultaAccess details = new ConsultaAccess(consultaDetails);

            access.setPaciente(details.getPaciente());
            access.setDataConsulta(details.getDataConsulta());
            access.setDiagnosticos(details.getDiagnosticos());
            access.setExames(details.getExames());
            access.setPrescricoes(details.getPrescricoes());

            return ResponseEntity.ok(consultaService.save(consulta));
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
package com.prontuarioMedico.controllers;

import com.prontuarioMedico.dto.ConsultaDto;
import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.service.ArmazenamentoService;
import com.prontuarioMedico.service.ConsultaService;
import com.prontuarioMedico.service.GerarConsultaPdfService;
import com.prontuarioMedico.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ArmazenamentoService armazenamentoService;

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private GerarConsultaPdfService gerarConsultaPdfService;


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
        ConsultaDto savedConsulta = consultaService.save(consultaDto);
        return ResponseEntity.ok(savedConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> updateConsulta(
            @PathVariable Long id,
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

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getConsultaPdf(@RequestParam String cpf) {
        Optional<PacienteDto> pacienteDtoOptional = pacienteService.findByCpf(cpf);
        if (pacienteDtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PacienteDto pacienteDto = pacienteDtoOptional.get();
        ConsultaDto consultaDto = new ConsultaDto();
        consultaDto.setPaciente(pacienteDto);

        try {
            byte[] pdfBytes = gerarConsultaPdfService.generatePdf(consultaDto);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "anexo; filename=consulta.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
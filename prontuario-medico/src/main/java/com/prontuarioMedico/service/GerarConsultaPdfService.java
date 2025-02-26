package com.prontuarioMedico.service;

import com.prontuarioMedico.dto.ConsultaDto;
import com.prontuarioMedico.dto.DiagnosticoDto;
import com.prontuarioMedico.dto.ExameDto;
import com.prontuarioMedico.dto.PacienteDto;
import com.prontuarioMedico.dto.PrescricaoDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class GerarConsultaPdfService {

    private static final Logger logger = LoggerFactory.getLogger(GerarConsultaPdfService.class);

    public byte[] generatePdf(ConsultaDto consultaDto) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Consulta Detalhes:");
                contentStream.newLineAtOffset(0, -20);

                // Paciente detalhes
                PacienteDto paciente = consultaDto.getPaciente();
                contentStream.showText("Paciente: " + paciente.getNome());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("CPF: " + paciente.getCpf());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Data de Nascimento: " + paciente.getDataNascimento().toString());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Endereço: " + paciente.getEndereco());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Telefone: " + paciente.getTelefone());
                contentStream.newLineAtOffset(0, -20);

                // Consulta detalhes
                contentStream.showText("Data da Consulta: " + consultaDto.getDataConsulta().toString());
                contentStream.newLineAtOffset(0, -20);

                // Diagnosticos
                contentStream.showText("Diagnósticos:");
                for (DiagnosticoDto diagnostico : consultaDto.getDiagnosticos()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Descrição: " + diagnostico.getDescricao());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Data: " + diagnostico.getDataDiagnostico().toString());
                }

                // Prescricoes
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Prescrições:");
                for (PrescricaoDto prescricao : consultaDto.getPrescricoes()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Medicamento: " + prescricao.getMedicamento());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Dosagem: " + prescricao.getDosagem());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Data: " + prescricao.getDataPrescricao().toString());
                }

                // Exames
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Exames:");
                for (ExameDto exame : consultaDto.getExames()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Tipo: " + exame.getTipo());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Data: " + exame.getDataExame().toString());
                }

                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Erro ao gerar pdf  ConsultaDto: {}", consultaDto, e);
            throw new RuntimeException("Erro ao gerar  PDF", e);
        }
    }
}
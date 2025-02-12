package com.prontuarioMedico.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ArmazenamentoService {
    // Dependências necessárias (ex: cliente do S3)

    public String uploadFile(MultipartFile file) {
        // Lógica de upload para o serviço de nuvem, como S3
        // Retorne a URL da imagem armazenada.
        return null;
    }
}

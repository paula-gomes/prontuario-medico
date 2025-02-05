package com.prontuarioMedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.prontuarioMedico.entities")
@EnableJpaRepositories(basePackages = "com.prontuarioMedico.repositories")
public class ProntuarioMedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProntuarioMedicoApplication.class, args);
	}
}

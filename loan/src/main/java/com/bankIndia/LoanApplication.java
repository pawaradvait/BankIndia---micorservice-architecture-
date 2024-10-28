package com.bankIndia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bankIndia.dto.LoanProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@OpenAPIDefinition(
	info =@Info(
		title = "Loans microservice REST API Documentation",
		description = "BankIndia Loans microservice REST API Documentation",
		version = "v1",
		contact=@Contact(
			name="BankIndia Team",
			url="https://www.bankindia.com",
			email="bankIndai@gmail.com"
		)
	)
)
@EnableConfigurationProperties(value ={LoanProperties.class})
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}

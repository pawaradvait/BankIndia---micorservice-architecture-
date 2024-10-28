package com.bankIndia.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bankIndia.account.dto.AccountContactInfoDto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Account Service",
		version = "v1",
		description = "Account Service API Documentation",
		contact = @Contact(
    name = "advait pawar",
	email = "pawaradvait2003@gmail.com"
		)
	)
)
@EnableConfigurationProperties(value ={AccountContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")

public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}

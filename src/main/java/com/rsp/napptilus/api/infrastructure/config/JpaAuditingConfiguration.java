package com.rsp.napptilus.api.infrastructure.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

	@Value("${spring.application.name}")
	private String appName;

	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAware<String>() {

			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of(appName);
			}

		};
	}

}

package com.ms.membreservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.ms.membreservice.entity.Membre;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MembreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MembreServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RepositoryRestConfiguration configuration ){
		return args->{
			configuration.exposeIdsFor(Membre.class);
		};
	}
	@Configuration
	public class RestTemplateConfig {
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
	}

}

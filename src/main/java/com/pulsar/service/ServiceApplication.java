package com.pulsar.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.reactive.config.annotation.EnableReactivePulsar;

@SpringBootApplication
@EnableReactivePulsar
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}

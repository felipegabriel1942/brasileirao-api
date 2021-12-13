package com.felipegabriel.classificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RefreshScope
public class ClassificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassificationServiceApplication.class, args);
	}

}

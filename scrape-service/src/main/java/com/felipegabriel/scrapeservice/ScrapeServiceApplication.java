package com.felipegabriel.scrapeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
public class ScrapeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrapeServiceApplication.class, args);
	}

}

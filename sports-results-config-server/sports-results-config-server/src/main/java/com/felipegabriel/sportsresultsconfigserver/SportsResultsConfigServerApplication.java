package com.felipegabriel.sportsresultsconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SportsResultsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsResultsConfigServerApplication.class, args);
	}

}

package com.infy.cpc.fas.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.infy.cpc.fas"})
@EnableJpaRepositories( basePackages = "com.infy.cpc.fas.repository")
@EntityScan("com.infy.cpc.fas.entity")
public class CPCFASStarter {

	public static void main(String[] args) {
		SpringApplication.run(CPCFASStarter.class, args);
	}

}

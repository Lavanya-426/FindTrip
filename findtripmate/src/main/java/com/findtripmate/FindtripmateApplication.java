package com.findtripmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FindtripmateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindtripmateApplication.class, args);
	}

}

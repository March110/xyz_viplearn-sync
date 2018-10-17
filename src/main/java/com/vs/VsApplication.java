package com.vs;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class VsApplication {

	public static void main(String[] args) {

		SpringApplication.run(VsApplication.class, args);
	}
}

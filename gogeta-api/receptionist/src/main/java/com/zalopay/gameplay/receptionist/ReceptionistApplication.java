package com.zalopay.gameplay.receptionist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("local")
public class ReceptionistApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReceptionistApplication.class, args);
	}

}

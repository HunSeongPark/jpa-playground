package com.example.jpa_playground;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaPlaygroundApplication.class, args);
	}

}

package com.alvin.tugaspostgre.tugasusertodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class TugasUserTodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TugasUserTodoApplication.class, args);
	}
}

package com.mygymroutine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mygymroutine.auth.AuthenticationService;
import com.mygymroutine.auth.RegisterRequest;
import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.rCaller.Prueba;

@SpringBootApplication
public class MyGymRoutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGymRoutineApplication.class, args);
		
		Prueba prueba = new Prueba();
	}
	
	
}

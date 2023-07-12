package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendJavaApplication {
	
	public static void main(String[] args) {
		System.out.println("Hello Spring!");
		System.out.println("Spring boot");
		SpringApplication.run(BackendJavaApplication.class, args);
	}

}

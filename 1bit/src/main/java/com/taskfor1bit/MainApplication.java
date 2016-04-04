package com.taskfor1bit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(MainApplication.class, args);
	}
}

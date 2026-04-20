package com.ConsoleRPGGame;

import com.ConsoleRPGGame.core.GameEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsoleRpgGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleRpgGameApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(GameEngine gameEngine) {
		return args -> {

			gameEngine.start();
		};
	}
}



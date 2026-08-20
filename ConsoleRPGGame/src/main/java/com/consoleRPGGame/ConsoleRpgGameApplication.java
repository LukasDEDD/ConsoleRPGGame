package com.consoleRPGGame;

import com.consoleRPGGame.application.GameEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ConsoleRpgGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleRpgGameApplication.class, args);
	}

	@Bean
	@Profile("!test")

	public CommandLineRunner run(GameEngine gameEngine) {
		return args -> {

			gameEngine.start();
		};
	}
}



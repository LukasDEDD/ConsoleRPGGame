package com.consoleRPGGame;

import com.consoleRPGGame.application.GameEngine;
import com.consoleRPGGame.infrastructure.input.InputReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class ConsoleRpgGameApplicationTests {

	@MockitoBean
	private InputReader inputReader;

	@MockitoBean
	private GameEngine gameEngine;


	@Test
	void contextLoads() {
	}

}

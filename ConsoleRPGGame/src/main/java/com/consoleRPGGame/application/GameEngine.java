package com.consoleRPGGame.application;

import com.consoleRPGGame.domain.creature.CreatureFactory;
import com.consoleRPGGame.domain.creature.enemy.Enemy;
import com.consoleRPGGame.infrastructure.input.InputReader;
import com.consoleRPGGame.infrastructure.output.ConsoleRenderer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")

public class GameEngine {
  private final InputReader input;
  private final ConsoleRenderer renderer;
  private final CombatService combatService;
  private final CreatureFactory creatureFactory;
  private final GameState gameState;

  public GameEngine(InputReader input, ConsoleRenderer renderer, CombatService combatService, CreatureFactory creatureFactory, GameState gameState) {
    this.input = input;
    this.renderer = renderer;
    this.combatService = combatService;
    this.creatureFactory = creatureFactory;
    this.gameState = gameState;
  }

  public void start() {
    renderer.renderMessage("Welcome to the adventure!");

    while (!gameState.isGameOver()) {
      renderer.renderMenu();
      String command = input.getCommand();


      processCommand(command);
    }
  }


  private void processCommand(String command) {
    switch (command) {
      case "1" -> {
        Enemy monster = creatureFactory.createOrc();
        renderer.renderMessage("You are meeting with" + monster.getName());
        combatService.startCombat(gameState.getActivePlayer(), monster);
      }
      case "2" -> {
        renderer.renderProfile(gameState.getActivePlayer());
      }
      case "3" -> {
        renderer.renderMessage("Ending the Game...");
        gameState.setGameOver(true);
      }
      default -> renderer.renderMessage("Invalid command!");
    }
  }
}




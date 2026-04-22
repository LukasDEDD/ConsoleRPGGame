package com.ConsoleRPGGame.core;

import com.ConsoleRPGGame.application.CombatService;

import com.ConsoleRPGGame.domain.creature.CreatureFactory;
import com.ConsoleRPGGame.domain.creature.Enemy;
import com.ConsoleRPGGame.infrastructure.input.InputReader;
import com.ConsoleRPGGame.infrastructure.output.ConsoleRenderer;
import org.springframework.stereotype.Component;

@Component
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




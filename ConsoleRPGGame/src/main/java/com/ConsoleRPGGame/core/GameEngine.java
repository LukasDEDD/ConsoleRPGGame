package com.ConsoleRPGGame.core;

import com.ConsoleRPGGame.domain.combat.CombatService;

import com.ConsoleRPGGame.domain.creature.CreatureFactory;
import com.ConsoleRPGGame.domain.creature.Enemy;
import com.ConsoleRPGGame.domain.creature.Player;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GameEngine {

  private final CombatService combatService;
  private final GameState gameState;
  private final CreatureFactory creatureFactory;
  private final Scanner scanner = new Scanner(System.in);

  public GameEngine(CombatService combatService, GameState gameState, CreatureFactory creatureFactory) {
    this.combatService = combatService;
    this.gameState = gameState;
    this.creatureFactory = creatureFactory;
  }

  public void start() {

    if (gameState.getActivePlayer() == null) {
      gameState.setActivePlayer(creatureFactory.createPlayer("Hero"));
    }

    while (!gameState.isGameOver()) {
      System.out.println("\n--- MAIN MENU ---");
      System.out.println("1 - Explore (Fight)");
      System.out.println("2 - View Profile");
      System.out.println("3 - Exit");
      System.out.print("Your choice: ");

      String input = scanner.nextLine();
      processInput(input);
    }
  }

  private void processInput(String input) {
    switch (input) {
      case "1" -> {

        Enemy monster = creatureFactory.createOrc();

        System.out.println("You encountered a " + monster.getName() + "!");
        combatService.startCombat(gameState.getActivePlayer(), monster);
      }
      case "2" -> {
        showProfile();
      }
      case "3" -> {
        System.out.println("Saving and exiting... Goodbye!");
        gameState.setGameOver(true);
      }
      default -> System.out.println("Invalid option. Please press 1, 2, or 3.");
    }
  }

  private void showProfile() {
    Player p = gameState.getActivePlayer();
    System.out.println("\n*************************");
    System.out.println("CHARACTER: " + p.getName());
    System.out.println("HP: " + p.getHealthPoints());
    System.out.println("STR: " + p.getStrength());
    System.out.println("DEF: " + p.getDefense());
    System.out.println("*************************");
  }
}



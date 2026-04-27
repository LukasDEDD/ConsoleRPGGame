package com.ConsoleRPGGame.infrastructure.output;

import com.ConsoleRPGGame.domain.creature.player.Player;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRenderer {

  public void renderMessage(String message) {
    System.out.println("[GAME]: " + message);
  }

  public void renderMenu() {
    System.out.println("\n--- MAIN MENU ---");
    System.out.println("1 - Explore (Battle)");
    System.out.println("2 - View Character Profile");
    System.out.println("3 - Exit Game");
    System.out.print("Choose your action: ");
  }

  public void renderProfile(Player player) {
    if (player == null) {
      System.out.println("Error: No hero found!");
      return;
    }

    System.out.println("\n==============================");
    System.out.println("       CHARACTER PROFILE      ");
    System.out.println("==============================");
    System.out.printf("Name:       %s%n", player.getName());
    System.out.printf("Level:      %d%n", player.getLevel());
    System.out.printf("Health:     %d / %d%n", player.getHealthPoints(), player.getMaxHealthPoints());
    System.out.printf("Strength:   %d%n", player.getStrength());
    System.out.printf("Defense:    %d%n", player.getDefense());
    System.out.printf("Experience: %d%n", player.getExperience());
    System.out.println("==============================\n");
  }
}
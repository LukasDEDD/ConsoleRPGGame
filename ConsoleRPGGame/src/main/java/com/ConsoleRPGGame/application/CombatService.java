package com.ConsoleRPGGame.application;

import com.ConsoleRPGGame.domain.creature.enemy.EnemyRepository;
import com.ConsoleRPGGame.domain.creature.player.PlayerRepository;
import com.ConsoleRPGGame.domain.creature.Creature;
import com.ConsoleRPGGame.domain.creature.player.Player;
import org.springframework.stereotype.Service;

@Service
public class CombatService {

  private final PlayerRepository playerRepository;
  private final EnemyRepository enemyRepository;

  public CombatService(PlayerRepository playerRepository,
                       EnemyRepository enemyRepository) {
    this.playerRepository = playerRepository;
    this.enemyRepository = enemyRepository;
  }

  public void startCombat(Player player, Creature creature) {

    System.out.println("The duel begins.");

    while (player.isAlive() && creature.isAlive()) {

      player.attack(creature);

      if (creature.isAlive()) {
        creature.attack(player);
      }

      printStatus(player, creature);
    }

    finalizeCombat(player, creature);
  }

  private void printStatus(Player player, Creature creature) {
    System.out.println("Status -> " + player.getName() + ": " + player.getHealthPoints() + " HP | "
      + creature.getName() + ": " + creature.getHealthPoints() + " HP");
  }

  private void finalizeCombat(Player player, Creature creature) {
    if (!player.isAlive()) {
      System.out.println("GAME OVER: " + player.getName() + " has fallen.");
    } else {
      System.out.println("VICTORY: " + creature.getName() + " has been defeated!");
    }

    playerRepository.save(player);
  }
}

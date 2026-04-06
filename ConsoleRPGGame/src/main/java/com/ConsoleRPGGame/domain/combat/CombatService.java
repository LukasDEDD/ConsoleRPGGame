package com.ConsoleRPGGame.domain.combat;


import com.ConsoleRPGGame.Repositories.EnemyRepository;
import com.ConsoleRPGGame.Repositories.PlayerRepository;
import com.ConsoleRPGGame.domain.creature.Creature;
import com.ConsoleRPGGame.domain.creature.Player;
import org.springframework.stereotype.Service;

@Service
public class CombatService {

  private final PlayerRepository playerRepository;

  private final EnemyRepository enemyRepository;

  public CombatService(PlayerRepository playerRepository, EnemyRepository enemyRepository) {
    this.playerRepository = playerRepository;
    this.enemyRepository = enemyRepository;

  }

  public void executeAttack(Creature attacker, Creature defender) {

    int damage = attacker.getAttackStrategy().calculateDamage(attacker, defender);

    int newHp = defender.getHealthPoints() - damage;
    defender.setHealthPoints(Math.max(0, newHp));

    System.out.println(attacker.getName() + "dealt" + damage + "character damage" + defender.getName());
  }

  public  void startCombat(Player player, Creature creature) {

    System.out.println("The duel begins.");

    while ( player.getHealthPoints()>0 && creature.getHealthPoints()>0 ) {

      executeAttack(player, creature);

      if ( creature.getHealthPoints() > 0) {
        executeAttack(creature, player);
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
    if (player.getHealthPoints() <= 0) {
      System.out.println("GAME OVER: " + player.getName() + " has fallen.");
    } else {
      System.out.println("VICTORY: " + creature.getName() + " has been defeated!");

    }

    playerRepository.save(player);
  }
  }








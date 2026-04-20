package com.ConsoleRPGGame;


import com.ConsoleRPGGame.Repositories.EnemyRepository;
import com.ConsoleRPGGame.Repositories.PlayerRepository;
import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import com.ConsoleRPGGame.domain.combat.CombatService;
import com.ConsoleRPGGame.domain.creature.Enemy;
import com.ConsoleRPGGame.domain.creature.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CombatServiceTest {

  @Mock
  private PlayerRepository playerRepository;

  @Mock
  private EnemyRepository enemyRepository;

  @InjectMocks
  private CombatService combatService;

  private Player player;
  private Enemy enemy;

  @BeforeEach
  void setUp() {

    AttackStrategy testStrategy = (attacker, defender) -> {
      int damage = attacker.getStrength() - defender.getDefense();
      return Math.max(0, damage);
    };

    player = new Player(
      "Hero",         // name
      100,            // healthPoints
      100,            // maxHealthPoints
      20,             // strength
      5,              // defense
      testStrategy,           // attackStrategy
      0,              // experience
      1,              // level
      new ArrayList<>(), // inventory (empty list)
      0,              // gold
      null,           // equippedWeapon
      null            // equipItem
    );
    enemy = new Enemy("Small Goblin", 50,                 // healthPoints
      50,                 // maxHealthPoints
      15,                 // strength
      5,                  // defense
      testStrategy,               // attackStrategy
      100                 // experience
    );
  };


  @Test
  @DisplayName("Attack should reduce enemy health correctly based on strength and defense")
  void testAttackReducesHealth() {

    int startingHp = enemy.getHealthPoints();

    combatService.executeAttack(player, enemy);

    assertEquals(35, enemy.getHealthPoints(), "Goblin should have 12 HP left after attack");
  }

  @Test
  @DisplayName("Dead entities should not be able to deal damage")
  void testDeadEntityCannotAttack() {



    enemy.setHealthPoints(0);
    int playerHpBefore = player.getHealthPoints();

    combatService.executeAttack(enemy, player);

    assertEquals(playerHpBefore, player.getHealthPoints(), "Player should not take damage from a dead enemy");
  }

  @Test
  @DisplayName("Health should never drop below zero")
  void testHealthDoesNotGoNegative() {

    player.setStrength(999);

    combatService.executeAttack(player, enemy);

    assertTrue(enemy.getHealthPoints() >= 0, "Health points should not be negative");
    assertEquals(0, enemy.getHealthPoints());
  }
}
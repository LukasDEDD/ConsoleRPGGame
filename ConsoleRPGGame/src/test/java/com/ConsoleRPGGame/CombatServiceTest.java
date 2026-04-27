package com.ConsoleRPGGame;

import com.ConsoleRPGGame.application.CombatService;
import com.ConsoleRPGGame.domain.creature.enemy.EnemyRepository;
import com.ConsoleRPGGame.domain.creature.player.PlayerRepository;
import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import com.ConsoleRPGGame.domain.creature.enemy.Enemy;
import com.ConsoleRPGGame.domain.creature.player.Player;
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
      "Hero",
      100,
      100,
      20,
      5,
      testStrategy,
      0,
      1,
      new ArrayList<>(),
      0,
      null
    );

    enemy = new Enemy(
      "Small Goblin",
      50,
      50,
      15,
      5,
      testStrategy,
      100
    );
  }

  @Test
  @DisplayName("Player attack should reduce enemy HP correctly")
  void testAttackReducesHealth() {

    int startingHp = enemy.getHealthPoints();

    player.attack(enemy);

    assertEquals(startingHp - 15, enemy.getHealthPoints());
  }

  @Test
  @DisplayName("Dead entity cannot attack")
  void testDeadEntityCannotAttack() {

    enemy.setHealthPoints(0);
    int hpBefore = player.getHealthPoints();

    enemy.attack(player);

    assertEquals(hpBefore, player.getHealthPoints());
  }

  @Test
  @DisplayName("Health should never drop below zero")
  void testHealthDoesNotGoNegative() {

    player.setStrength(999);

    player.attack(enemy);

    assertEquals(0, enemy.getHealthPoints());
  }
}

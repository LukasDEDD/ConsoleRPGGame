package com.ConsoleRPGGame.domain.creature;

import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import com.ConsoleRPGGame.domain.item.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreatureFactory {

  private final AttackStrategy defaultStrategy = (attacker, defender) -> {
    int damage = attacker.getStrength() - defender.getDefense();
    return Math.max(0, damage);
  };

  public Player createPlayer(String name) {
    List<Item> inventory = new ArrayList<>();

    return new Player(
      name,
      100,
      100,
      20,
      10,
      defaultStrategy,
      0,
      1,
      inventory,
      10,
      null                 // equippedWeapon
    );
  }

  public Enemy createCreature() {
    return new Enemy(
      "Weak Goblin",
      30,
      30,
      8,
      2,
      defaultStrategy,
      10
    );
  }

  public Enemy createOrc() {
    return new Enemy(
      "Furious Orc",
      70,
      70,
      18,
      6,
      defaultStrategy,
      10
    );
  }

  public Enemy createEnemy(String type) {
    return switch (type.toUpperCase()) {
      case "ORC" -> createOrc();
      default -> createCreature();
    };
  }
}

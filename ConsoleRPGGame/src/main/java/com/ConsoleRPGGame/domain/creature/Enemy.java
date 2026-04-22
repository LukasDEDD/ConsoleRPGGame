package com.ConsoleRPGGame.domain.creature;

import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import jakarta.persistence.*;

@Entity
@Table(name = "enemy")
public class Enemy extends Creature {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int experience;

  public Enemy() {
  }

  public Enemy(String name,
               int healthPoints,
               int maxHealthPoints,
               int strength,
               int defense,
               AttackStrategy attackStrategy,
               int experience) {

    super(name, healthPoints, maxHealthPoints, strength, defense, attackStrategy);
    this.experience = experience;
  }

  // může používat attack() z Creature



  public Long getId() {
    return id;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }
}

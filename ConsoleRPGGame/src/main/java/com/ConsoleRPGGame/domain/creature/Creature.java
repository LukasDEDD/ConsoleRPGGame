package com.ConsoleRPGGame.domain.creature;

import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class Creature {

  private String name;
  private int healthPoints;
  private int maxHealthPoints;
  private int strength;
  private int defense;

  @Transient
  private AttackStrategy attackStrategy;


  public Creature() {
  }

  public Creature(String name,
                  int healthPoints,
                  int maxHealthPoints,
                  int strength,
                  int defense,
                  AttackStrategy attackStrategy) {

    this.name = name;
    this.healthPoints = healthPoints;
    this.maxHealthPoints = maxHealthPoints;
    this.strength = strength;
    this.defense = defense;
    this.attackStrategy = attackStrategy;
  }

  // ---------- DDD Logika ----------

  public boolean isAlive() {
    return this.healthPoints > 0;
  }

  public void attack(Creature target) {
    if (!this.isAlive()) {
      return; // mrtvý neútočí
    }

    int damage = attackStrategy.calculateDamage(this, target);
    target.takeDamage(damage);
  }

  public void takeDamage(int amount) {
    if (!this.isAlive()) {
      return; // mrtvý už nedostává damage
    }

    this.healthPoints = Math.max(0, this.healthPoints - amount);
  }

  // ---------- Gettery / Settery ----------

  public String getName() {
    return name;
  }

  public int getHealthPoints() {
    return healthPoints;
  }

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = Math.max(0, healthPoints);
  }

  public int getMaxHealthPoints() {
    return maxHealthPoints;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getDefense() {
    return defense;
  }

  public AttackStrategy getAttackStrategy() {
    return attackStrategy;
  }

  public void setAttackStrategy(AttackStrategy attackStrategy) {
    this.attackStrategy = attackStrategy;
  }
}

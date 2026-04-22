package com.ConsoleRPGGame.domain.creature;

import com.ConsoleRPGGame.domain.combat.AttackStrategy;

public abstract class Creature {

  protected String name;
  protected int healthPoints;
  protected int maxHealthPoints;
  protected int strength;
  protected int defense;

  protected AttackStrategy attackStrategy;

  protected Creature() {
  }

  protected Creature(String name,
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

  //  DDD logika společná pro všechny bytosti

  public void attack(Creature target) {
    int damage = this.attackStrategy.calculateDamage(this, target);
    target.takeDamage(damage);
  }

  public void takeDamage(int dmg) {
    this.healthPoints = Math.max(0, this.healthPoints - dmg);
  }

  public boolean isAlive() {
    return this.healthPoints > 0;
  }



  public String getName() {
    return name;
  }

  public int getHealthPoints() {
    return healthPoints;
  }

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  public int getMaxHealthPoints() {
    return maxHealthPoints;
  }

  public void setMaxHealthPoints(int maxHealthPoints) {
    this.maxHealthPoints = maxHealthPoints;
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

  public void setDefense(int defense) {
    this.defense = defense;
  }

  public AttackStrategy getAttackStrategy() {
    return attackStrategy;
  }

  public void setAttackStrategy(AttackStrategy attackStrategy) {
    this.attackStrategy = attackStrategy;
  }
}

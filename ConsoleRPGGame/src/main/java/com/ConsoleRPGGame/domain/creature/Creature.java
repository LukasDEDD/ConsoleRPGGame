package com.ConsoleRPGGame.domain.creature;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;

@MappedSuperclass
public abstract class Creature {

  @Column(nullable = false)

  private String name;

  private int healthPoints;
  private int maxHealthPoints;
  private int strength;
  private int defense;

  public Creature() {
  }

  public Creature(String name, int healthPoints, int maxHealthPoints, int strength, int defense) {
    this.name = name;
    this.healthPoints = healthPoints;
    this.maxHealthPoints = maxHealthPoints;
    this.strength = strength;
    this.defense = defense;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}

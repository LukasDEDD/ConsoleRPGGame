package com.ConsoleRPGGame.domain.creature;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "players")

public class Player extends Creature {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private int experience = 0;
  private int level = 1;
  private int gold = 10;

  public Player() {
  }

  public Player(String name, int healthPoints, int maxHealthPoints, int strength, int defense) {
    super(name, healthPoints, maxHealthPoints, strength, defense);

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }
}

package com.ConsoleRPGGame.domain.creature;


import jakarta.persistence.*;

@Entity
@Table(name = "enemy")
public class Enemy extends Creature {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int experienceReward = 20;

  public Enemy() {
  }

  public Enemy(String name, int healthPoints, int maxHealthPoints, int strength, int defense, int experienceReward) {
    super(name, healthPoints, maxHealthPoints, strength, defense);

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getExperienceReward() {
    return experienceReward;
  }

  public void setExperienceReward(int experienceReward) {
    this.experienceReward = experienceReward;
  }
}

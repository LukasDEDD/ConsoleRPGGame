package com.ConsoleRPGGame.domain.creature;


import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import com.ConsoleRPGGame.domain.item.Item;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")

public class Player extends Creature {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private int experience = 0;
  private int level = 1;
  private int gold = 10;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "player_id")
  private List<Item> inventory = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "equipped_weapon_id")
  private Item equippedWeapon;

  @ManyToOne
  @JoinColumn(name = "equipItem_id")
  private Item equipItem;

  private AttackStrategy attackStrategy;


  public Player(String name, int healthPoints, int maxHealthPoints, int strength, int defense, AttackStrategy attackStrategy, Item equipItem, Item equippedWeapon, List<Item> inventory, int gold, int level, int experience) {
    super(name, healthPoints, maxHealthPoints, strength, defense);
    this.attackStrategy = attackStrategy;
    this.equipItem = equipItem;
    this.equippedWeapon = equippedWeapon;
    this.inventory = inventory;
    this.gold = gold;
    this.level = level;
    this.experience = experience;
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

  public List<Item> getInventory() {
    return inventory;
  }

  public void setInventory(List<Item> inventory) {
    this.inventory = inventory;
  }

  public Item getEquippedWeapon() {
    return equippedWeapon;
  }

  public void setEquippedWeapon(Item equippedWeapon) {
    this.equippedWeapon = equippedWeapon;
  }

  public Item getEquipItem() {
    return equipItem;
  }

  public void setEquipItem(Item equipItem) {
    this.equipItem = equipItem;
  }

  public AttackStrategy getAttackStrategy() {
    return attackStrategy;
  }

  public void setAttackStrategy(AttackStrategy attackStrategy) {
    this.attackStrategy = attackStrategy;
  }
}

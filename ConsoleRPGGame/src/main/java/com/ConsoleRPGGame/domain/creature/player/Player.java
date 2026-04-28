package com.ConsoleRPGGame.domain.creature.player;

import com.ConsoleRPGGame.domain.combat.AttackStrategy;
import com.ConsoleRPGGame.domain.creature.Creature;
import com.ConsoleRPGGame.domain.item.Item;
import com.ConsoleRPGGame.domain.item.ItemType;
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

  public Player() {
  }

  public Player(String name,
                int healthPoints,
                int maxHealthPoints,
                int strength,
                int defense,
                AttackStrategy attackStrategy,
                int experience,
                int level,
                List<Item> inventory,
                int gold,
                Item equippedWeapon) {

    super(name, healthPoints, maxHealthPoints, strength, defense, attackStrategy);
    this.experience = experience;
    this.level = level;
    this.inventory = inventory;
    this.gold = gold;
    this.equippedWeapon = equippedWeapon;
  }



  @Override
  public void attack(Creature target) {
    int damage = this.getAttackStrategy().calculateDamage(this, target);
    target.takeDamage(damage);
  }

  public void equipWeapon(Item item) {
    if (item.getType() != ItemType.WEAPON) {
      throw new IllegalArgumentException("Item is not a weapon");
    }
    this.equippedWeapon = item;
  }

  public void unequipWeapon() {
    this.equippedWeapon = null;
  }

  public void addItem(Item item) {
    this.inventory.add(item);
  }

  public void removeItem(Item item) {
    this.inventory.remove(item);
  }

  public void useItem(Item item) {
    switch (item.getType()) {
      case POTION:
        int heal = item.getPower();
        int newHp = Math.min(this.getMaxHealthPoints(), this.getHealthPoints() + heal);
        this.setHealthPoints(newHp);
        this.inventory.remove(item);
        break;
      case WEAPON:
        equipWeapon(item);
        break;
      default:
        throw new IllegalStateException("This item cannot be used.");
    }
  }



  public Long getId() {
    return id;
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
}

package com.ConsoleRPGGame.application;

import com.ConsoleRPGGame.application.repository.ItemRepository;
import com.ConsoleRPGGame.application.repository.PlayerRepository;
import com.ConsoleRPGGame.domain.creature.Player;
import com.ConsoleRPGGame.domain.item.Item;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
  private final ItemRepository itemRepository;
  private final PlayerRepository playerRepository;

  public InventoryService(ItemRepository itemRepository, PlayerRepository playerRepository) {
    this.itemRepository = itemRepository;
    this.playerRepository = playerRepository;
  }

  private void applyPotionEffect(Player player, Item potion) {
    int currentHp = player.getHealthPoints();
    int healAmount = potion.getPower();
    int newHp = Math.min(player.getMaxHealthPoints(), currentHp + healAmount);

    player.setHealthPoints(newHp);

    player.getInventory().remove(potion);

    System.out.println("You drank" + potion.getName() + ". Cured: " + healAmount + " HP.");
  }


  public void equipWeapon(Player player, Item weapon) {

      player.setEquippedWeapon(weapon);
      System.out.println("You have deployed: " + weapon.getName());
    }

  public void equipItem(Player player, Item weapon) {
    player.setEquippedWeapon(weapon);
    System.out.println("You have deployed: " + weapon.getName());
  }
// remove() (pro Seznamy / Batoh) Když máš hromadu věcí a chceš jednu z nich úplně vyndat
  // Seznam se "smrskne". Pokud jsi měl 5 věcí a jednu jsi remove, máš jich 4.


  // null (pro Jednotlivá políčka / Ruku)
   // Políčko tam zůstane, ale je "vynulované" (prázdné). Neříkáš "smaž tu ruku", ale "dej z té ruky pryč ten meč


  public void unequipWeapon(Player player) {
    player.setEquippedWeapon(null);
    System.out.println("Weapon removed.");
  }

  public void unequipItem(Player player) {
    player.setEquippedWeapon(null);
    System.out.println("Weapon removed.");
  }

 public void addItemToPlayer(Player player, Long itemId) {
   Item item = itemRepository.findById(itemId)
     .orElseThrow(() -> new RuntimeException("The item does not exist!"));

   player.getInventory().add(item);
   playerRepository.save(player);
   System.out.println("You have earned: " + item.getName());
 }



  public void  removeItem(Player player, Long itemId) {
    Item item = itemRepository.findById(itemId)
      .orElseThrow(() -> new RuntimeException("The item does not exist!"));

    boolean removed = player.getInventory().remove(item);
    // metoda .remove() v Javě ti sama odpovídá, jestli se jí povedlo tu věc najít a smazat.

    if (removed) {

      playerRepository.save(player);
      System.out.println("Object " + item.getName() + " has been removed from inventory.");
    } else {
      System.out.println("The player does not have this item on them!");
    }

  }

  public void useItem(Player player, Long itemId) {
    Item item = itemRepository.findById(itemId)
      .orElseThrow(() -> new RuntimeException("The item does not exist!"));

    switch (item.getType()) {
      case POTION:
        applyPotionEffect(player, item);
        break;

      case WEAPON:
        equipWeapon(player, item);
        break;

      default:
        System.out.println("This item cannot be used.");
    }
    playerRepository.save(player);
  }

}

package com.ConsoleRPGGame.application;

import com.ConsoleRPGGame.domain.item.ItemRepository;
import com.ConsoleRPGGame.domain.creature.player.PlayerRepository;
import com.ConsoleRPGGame.domain.creature.player.Player;
import com.ConsoleRPGGame.domain.item.Item;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  private final ItemRepository itemRepository;
  private final PlayerRepository playerRepository;

  public InventoryService(ItemRepository itemRepository,
                          PlayerRepository playerRepository) {
    this.itemRepository = itemRepository;
    this.playerRepository = playerRepository;
  }

  public void equipWeapon(Player player, Long itemId) {
    Item weapon = itemRepository.findById(itemId)
      .orElseThrow(() -> new RuntimeException("The item does not exist!"));

    player.equipWeapon(weapon);
    playerRepository.save(player);
    System.out.println("You have deployed: " + weapon.getName());
  }

  public void unequipWeapon(Player player) {
    player.unequipWeapon();
    playerRepository.save(player);
    System.out.println("Weapon removed.");
  }

  public void addItemToPlayer(Player player, Long itemId) {
    Item item = itemRepository.findById(itemId)
      .orElseThrow(() -> new RuntimeException("The item does not exist!"));

    player.addItem(item);
    playerRepository.save(player);
    System.out.println("You have earned: " + item.getName());
  }

  public void removeItem(Player player, Long itemId) {
    Item item = itemRepository.findById(itemId)
      .orElseThrow(() -> new RuntimeException("The item does not exist!"));

    boolean removed = player.getInventory().remove(item);

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

    player.useItem(item);
    playerRepository.save(player);
  }
}

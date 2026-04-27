package com.ConsoleRPGGame.application;

import com.ConsoleRPGGame.domain.creature.Creature;
import com.ConsoleRPGGame.domain.creature.player.Player;
import org.springframework.stereotype.Component;


@Component
public class GameState {
  private Player activePlayer;
  private Creature currentEnemy;
  private boolean isGameOver = false;
  private String lastMessage;

  public GameState() {

  }

  public String getLastMessage() {
    return lastMessage;
  }

  public void setLastMessage(String lastMessage) {
    this.lastMessage = lastMessage;
  }

  public boolean isGameOver() {
    return isGameOver;
  }

  public void setGameOver(boolean gameOver) {
    isGameOver = gameOver;
  }

  public Creature getCurrentEnemy() {
    return currentEnemy;
  }

  public void setCurrentEnemy(Creature currentEnemy) {
    this.currentEnemy = currentEnemy;
  }

  public Player getActivePlayer() {
    return activePlayer;
  }

  public void setActivePlayer(Player activePlayer) {
    this.activePlayer = activePlayer;
  }
}
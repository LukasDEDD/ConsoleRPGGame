package com.ConsoleRPGGame.domain.creature;


import org.springframework.stereotype.Component;

@Component
public class CreatureFactory {

  public Player createPlayer (String name){
    return new Player (name, 100, 100, 15, 5);

  }

  public Enemy createGoblin (){

    return new Enemy ("Weak Goblin", 30, 30, 8, 2, 20 );

  }

  public Enemy createOrc () {
    return new Enemy("Furious Orc", 70, 70, 18, 6, 50);
  }

}

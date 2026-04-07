package com.ConsoleRPGGame.domain.creature;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class CreatureFactory {


  public Player createPlayer (String name){
    return new Player (name, 100, 100,20, 10, null, 0,1,new ArrayList<>(),10,null, null);

  }

  public Enemy createCreature (){

    return new Enemy ("Weak Goblin", 30, 30, 8, 2, null,10 );

  }

  public Enemy createOrc () {

    return new Enemy("Furious Orc", 70, 70, 18, 6, null,10);
  }

  public Enemy createEnemy(String type) {
    return switch (type.toUpperCase()) {
      case "ORC" -> createOrc();
      default -> createCreature(); // vrátí Goblina
    };
  }
}

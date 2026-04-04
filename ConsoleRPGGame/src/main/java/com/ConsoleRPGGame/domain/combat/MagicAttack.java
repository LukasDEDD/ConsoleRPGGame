package com.ConsoleRPGGame.domain.combat;


import com.ConsoleRPGGame.domain.creature.Creature;
import org.springframework.stereotype.Component;

@Component

public class MagicAttack implements AttackStrategy{

  @Override
  public int calculateDamage(Creature attacker, Creature defender) {

    int damage = attacker.getStrength() - (defender.getDefense()/2);

    return Math.max(0, damage);
  }
}

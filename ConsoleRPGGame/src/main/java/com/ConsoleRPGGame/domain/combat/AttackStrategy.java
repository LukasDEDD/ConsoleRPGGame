package com.ConsoleRPGGame.domain.combat;

import com.ConsoleRPGGame.domain.creature.Creature;

public interface AttackStrategy {
  int calculateDamage(Creature attacker, Creature defender);
}

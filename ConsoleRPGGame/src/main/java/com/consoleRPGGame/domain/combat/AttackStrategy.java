package com.consoleRPGGame.domain.combat;

import com.consoleRPGGame.domain.creature.Creature;

public interface AttackStrategy {
  int calculateDamage(Creature attacker, Creature defender);
}

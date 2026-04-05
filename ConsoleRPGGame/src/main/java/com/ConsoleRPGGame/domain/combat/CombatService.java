package com.ConsoleRPGGame.domain.combat;


import com.ConsoleRPGGame.Repositories.EnemyRepository;
import com.ConsoleRPGGame.Repositories.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class CombatService {

 private final PlayerRepository playerRepository;

 private final EnemyRepository enemyRepository;

  public CombatService(PlayerRepository playerRepository, EnemyRepository enemyRepository) {
    this.playerRepository = playerRepository;
    this.enemyRepository = enemyRepository;
  }




}

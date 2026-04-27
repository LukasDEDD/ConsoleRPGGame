package com.ConsoleRPGGame.domain.creature.enemy;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface  EnemyRepository extends JpaRepository<Enemy, Long> {



}

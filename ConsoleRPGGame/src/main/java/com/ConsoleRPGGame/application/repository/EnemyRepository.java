package com.ConsoleRPGGame.application.repository;


import com.ConsoleRPGGame.domain.creature.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface  EnemyRepository extends JpaRepository<Enemy, Long> {



}

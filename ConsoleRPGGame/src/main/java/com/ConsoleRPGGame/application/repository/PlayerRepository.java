package com.ConsoleRPGGame.application.repository;


import com.ConsoleRPGGame.domain.creature.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PlayerRepository extends JpaRepository<Player, Long> {


}

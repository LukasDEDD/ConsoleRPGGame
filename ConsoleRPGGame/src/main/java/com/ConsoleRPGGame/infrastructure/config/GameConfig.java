package com.ConsoleRPGGame.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class GameConfig {

  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }


  public static final int STARTING_HP = 100;
  public static final int STARTING_XP = 0;
  public static final double DODGE_CHANCE = 0.15;
}

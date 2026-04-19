package com.ConsoleRPGGame.infrastructure.input;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputReader {
  private final Scanner scanner;

  public InputReader(Scanner scanner) {
    this.scanner = scanner;
  }

  public String getCommand() {
    System.out.print("\n> ");
    return scanner.nextLine().trim().toLowerCase();
  }

  public int getIntInput() {
    while (!scanner.hasNextInt()) {
      System.out.println("That's not a number! Try again:");
      scanner.next();
    }
    return scanner.nextInt();
  }
}

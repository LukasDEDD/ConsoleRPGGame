 # Console RPG Game
A Spring Boot–powered text RPG demonstrating clean architecture, domain‑driven design, and automated testing.

## Features
Turn‑based Combat: Implemented using the AttackStrategy pattern for flexible damage calculation.

Clean Domain Model: Creatures, items, combat logic, and game engine separated into clear domain modules.

Factory Pattern: Enemy creation handled by CreatureFactory.

Inventory System: Basic item model with ItemType and InventoryService.

Console Rendering: Simple CLI output via ConsoleRenderer.

Unit Testing: Comprehensive test suite using JUnit 5 and Mockito.

## Tech Stack
* Java 25

* Spring Boot 3.5.13

* Spring Data JPA (Hibernate)

* H2 In‑Memory Database

* Maven

##  Architecture Overview

```
src/main/java/com.ConsoleRPGGame
│
├── core
│   ├── GameEngine
│   └── GameState
│
├── domain
│   ├── combat
│   │   ├── AttackStrategy
│   │   ├── CombatService
│   │   ├── MagicAttack
│   │   └── MeleeAttack
│   │
│   ├── creature
│   │   ├── Creature
│   │   ├── Player
│   │   ├── Enemy
│   │   └── CreatureFactory
│   │
│   └── item
│       ├── Item
│       ├── ItemType
│       └── InventoryService
│
├── infrastructure
│   ├── config
│   │   └── GameConfig
│   ├── input
│   │   └── InputReader
│   ├── output
│   │   └── ConsoleRenderer
│   └── Repositories
│
└── ConsoleRpgGameApplication
``` 
### This structure follows clean architecture principles:

* Domain contains pure game logic.

* Core manages the game loop and state.

* Infrastructure handles I/O, configuration, and persistence.

* Application bootstraps the Spring context.

## Combat System
### The combat engine ensures:

* Dead attackers cannot deal damage.

* Dead defenders cannot receive damage.

* Damage is calculated through a pluggable AttackStrategy.

* Health never drops below zero.

### Example logic:

### java

if (attacker.getHealthPoints() <= 0) return;

if (defender.getHealthPoints() <= 0) return;

int damage = attacker.getAttackStrategy().calculateDamage(attacker, defender);
defender.setHealthPoints(Math.max(0, defender.getHealthPoints() - damage));

## Testing
### Run all tests:

* bash
* mvn test
* The test suite includes:
* Combat logic validation
* Dead‑entity attack prevention
* Health boundary checks
* Repository mocking with Mockito

## Persistence
### The game uses:

* H2 in‑memory database for fast development
* Spring Data JPA repositories for Player and Enemy
* This allows future expansion into:
* Saving game progress
* Loading characters
* Persistent world state
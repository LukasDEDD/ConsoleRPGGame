# Console RPG Game

A Spring Boot–powered text RPG demonstrating **clean architecture**, **pragmatic domain‑driven design**, and **automated testing**.

---

## Features

- **Turn‑based Combat**  
  Combat logic lives directly inside the domain model (`Player.attack()`, `Creature.takeDamage()`), using the `AttackStrategy` pattern for flexible damage calculation.

- **Pragmatic DDD Domain Model**  
  Business logic is implemented inside domain entities (Player, Enemy, Creature).  
  Application services only orchestrate loading and saving entities.

- **Inventory System**  
  The player can equip weapons, use items, and manage inventory through domain methods.

- **Clean Architecture Layout**  
  Clear separation of domain, application, infrastructure, and core game loop.

- **Unit Testing**  
  JUnit 5 + Mockito tests validate combat logic, HP boundaries, and domain behavior.

---

## Tech Stack

- Java 25
- Spring Boot 3.5
- Spring Data JPA (Hibernate)
- H2 In‑Memory Database
- Maven

---
````
##  Architecture Overview

src/main/java/com.ConsoleRPGGame
│
├── core
│   ├── GameEngine
│   └── GameState
│
├── domain
│   ├── combat
│   │   ├── AttackStrategy
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
│       └── (inventory logic handled in Player)
│
├── application
│   ├── CombatService
│   ├── InventoryService
│   └── repository
│       ├── PlayerRepository
│       ├── EnemyRepository
│       └── ItemRepository
│
├── infrastructure
│   ├── config
│   ├── input
│   ├── output
│   └── persistence
│
└── ConsoleRpgGameApplication
````

### Why this is pragmatic DDD

- The **domain layer contains all business logic** (combat, damage, inventory actions).
- The **application layer orchestrates** domain behavior and repository access.
- The **infrastructure layer** handles I/O, persistence, and configuration.
- The **core layer** runs the game loop independently of Spring.

---

##  Combat System

The combat engine ensures:

- Dead attackers cannot deal damage
- Dead defenders cannot receive damage
- Damage is calculated via `AttackStrategy`
- Health never drops below zero

### Example domain logic

``` java
public void attack(Creature target) {
    int damage = attackStrategy.calculateDamage(this, target);
    target.takeDamage(damage);
}
```
## Testing

Run all tests:


The test suite includes:

- Combat logic validation  
- Dead‑entity attack prevention  
- Health boundary checks  
- Domain‑level behavior tests  
- Mockito repository mocking  

---

##  Persistence

The game uses:

- H2 in‑memory database  
- Spring Data JPA repositories  
- Automatic schema generation  

Prepared for future expansion:

- Saving game progress  
- Loading characters  
- Persistent world state  


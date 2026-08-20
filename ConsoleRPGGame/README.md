# Console RPG Game

A Domain-Driven Design (DDD) based console RPG application built with Java 21 and Spring Boot.

This project was created as a portfolio project to demonstrate practical understanding of **Domain-Driven Design, object-oriented programming, layered architecture, domain modelling, design patterns, persistence, testing, containerization and cloud deployment**.

The RPG domain is intentionally used as a small but realistic business domain where game rules are modelled inside the domain layer instead of being placed inside controllers or infrastructure code.

## Project Goals

The primary goal of this project is to demonstrate how a domain can be modelled using DDD principles.

The project focuses on:

* Domain modelling
* Separation of domain and infrastructure concerns
* Encapsulation of business rules
* Entities and domain behaviour
* Repository abstraction
* Domain services
* Factory pattern
* Strategy pattern
* Application services
* Dependency Injection
* Persistence with Spring Data JPA
* Automated testing
* Docker containerization
* CI/CD
* Azure Container Registry
* Azure Container Apps

The application is intentionally small so that the domain model and architectural boundaries remain easy to understand.

---

## Domain-Driven Design

The most important part of this project is the domain model.

The application is structured around the RPG domain rather than around technical concerns such as controllers or database tables.

```text
Domain
│
├── Creature
│   ├── Player
│   └── Enemy
│
├── Combat
│   └── AttackStrategy
│       ├── MeleeAttack
│       └── MagicAttack
│
└── Item
    ├── Item
    └── ItemType
```

The domain contains the rules that define how the game works.

For example:

* a dead creature cannot attack
* a dead creature cannot receive additional damage
* health cannot become negative
* a potion cannot increase health above maximum HP
* only weapons can be equipped as weapons
* different attack strategies calculate damage differently

These rules belong to the domain because they describe the behaviour of the business/domain model.

---

## DDD Layered Architecture

The project follows a DDD-oriented layered structure.

```text
src/main/java/com/consoleRPGGame
│
├── application
│   ├── CombatService
│   ├── GameEngine
│   ├── GameState
│   └── InventoryService
│
├── domain
│   ├── combat
│   │   ├── AttackStrategy
│   │   ├── MagicAttack
│   │   └── MeleeAttack
│   │
│   ├── creature
│   │   ├── Creature
│   │   ├── CreatureFactory
│   │   │
│   │   ├── enemy
│   │   │   ├── Enemy
│   │   │   └── EnemyRepository
│   │   │
│   │   └── player
│   │       ├── Player
│   │       └── PlayerRepository
│   │
│   └── item
│       ├── Item
│       ├── ItemRepository
│       └── ItemType
│
└── infrastructure
    ├── config
    │   └── GameConfig
    │
    ├── input
    │   └── InputReader
    │
    └── output
        └── ConsoleRenderer
```

### Domain Layer

The domain layer contains the core business rules.

Examples:

* `Creature`
* `Player`
* `Enemy`
* `Item`
* `AttackStrategy`
* `MeleeAttack`
* `MagicAttack`
* `CreatureFactory`

The domain should be understandable without needing to understand the console interface or the deployment environment.

### Application Layer

The application layer coordinates use cases.

Examples:

* `GameEngine`
* `CombatService`
* `InventoryService`
* `GameState`

Application services orchestrate operations but delegate domain decisions to domain objects.

### Infrastructure Layer

The infrastructure layer contains technical concerns.

Examples:

* console input
* console output
* Spring configuration
* persistence implementation

This separation prevents infrastructure concerns from becoming part of the domain model.

---

# Domain Model

## Creature

`Creature` is the base domain abstraction for entities that participate in combat.

It contains:

* name
* health points
* maximum health points
* strength
* defense
* attack strategy

More importantly, it contains domain behaviour.

```
public boolean isAlive() {
    return this.healthPoints > 0;
}
```

Damage is handled inside the domain:

```
public void takeDamage(int amount) {
    if (!this.isAlive()) {
        return;
    }

    this.healthPoints = Math.max(0, this.healthPoints - amount);
}
```

This is an important DDD principle demonstrated by the project:

> Domain objects contain behaviour and protect their own invariants.

The application layer does not directly manipulate HP using procedural logic.

Instead, it calls:

```
target.takeDamage(damage);
```

The domain object is responsible for maintaining its state correctly.

---

# Player

`Player` is a domain entity extending `Creature`.

A player contains:

* identity
* health
* strength
* defense
* experience
* level
* gold
* inventory
* equipped weapon

The player also contains domain behaviour.

For example:

```
public void equipWeapon(Item item) {
    if (item.getType() != ItemType.WEAPON) {
        throw new IllegalArgumentException("Item is not a weapon");
    }

    this.equippedWeapon = item;
}
```

The rule that only weapons can be equipped is therefore part of the domain model.

The application service does not need to know how the rule is implemented.

It simply calls:

```
player.equipWeapon(item);
```

---

# Item

Items belong to the game domain.

```java
public enum ItemType {
    WEAPON,
    ARMOR,
    POTION,
    QUEST_ITEM
}
```

The player can:

* add an item
* remove an item
* equip a weapon
* unequip a weapon
* use a potion
* use a weapon

Potion behaviour is also implemented in the domain.

```java
int newHp = Math.min(
    this.getMaxHealthPoints(),
    this.getHealthPoints() + heal
);
```

This protects the invariant that player HP cannot exceed maximum HP.

---

# Combat Domain

Combat is modelled separately from the application orchestration.

The combat calculation is represented by:

```java
public interface AttackStrategy {

    int calculateDamage(
        Creature attacker,
        Creature defender
    );
}
```

This allows combat behaviour to vary without changing the `Creature` abstraction.

## Strategy Pattern

The project currently contains:

```text
AttackStrategy
├── MeleeAttack
└── MagicAttack
```

### Melee Attack

```text
damage = attacker strength - defender defense
```

### Magic Attack

```text
damage = attacker strength - defender defense / 2
```

Both implementations prevent negative damage.

This demonstrates the Strategy Pattern and separates a domain rule from the entity that uses it.

---

# Domain Service

`CombatService` is located in the application layer and coordinates the combat use case.

```
public void startCombat(Player player, Creature creature)
```

The service coordinates the interaction:

```text
Start Combat
     |
     v
Player attacks
     |
     v
Enemy alive?
     |
    Yes
     |
     v
Enemy attacks
     |
     v
Display state
     |
     v
Repeat
```

The service does not calculate damage itself.

Instead, the domain objects perform the actual domain operations:

```
player.attack(creature);
creature.attack(player);
```

This keeps the combat rules inside the domain model while the application service coordinates the use case.

---

# Repository Abstraction

The project uses repository abstractions for persistence.

Examples:

```java
public interface PlayerRepository
        extends JpaRepository<Player, Long> {
}
```

```java
public interface EnemyRepository
        extends JpaRepository<Enemy, Long> {
}
```

```java
public interface ItemRepository
        extends JpaRepository<Item, Long> {
}
```

Repositories provide a boundary between the domain/application model and persistence.

Spring Data JPA provides the infrastructure implementation.

This allows the application code to work with repository abstractions instead of directly managing database operations.

---

# Factory Pattern

`CreatureFactory` is responsible for creating domain objects with valid initial state.

Example:

```java
public Enemy createOrc() {
    return new Enemy(
        "Furious Orc",
        70,
        70,
        18,
        6,
        defaultStrategy,
        10
    );
}
```

The factory keeps object creation out of `GameEngine`.

This makes the application layer independent of the concrete construction details of enemies.

---

# Application Layer

The application layer contains the use-case orchestration.

## GameEngine

`GameEngine` controls the main application flow.

It:

* displays the menu
* receives user input
* starts battles
* displays the player profile
* ends the game

Spring Boot starts the game using `CommandLineRunner`.

```java
@Bean
public CommandLineRunner run(GameEngine gameEngine) {
    return args -> gameEngine.start();
}
```

## InventoryService

`InventoryService` coordinates inventory-related use cases.

Examples:

```text
equipWeapon()
unequipWeapon()
addItemToPlayer()
removeItem()
useItem()
```

The service delegates the actual domain rules to the `Player` entity.

---

# Infrastructure Layer

Infrastructure contains technical concerns that are not part of the core domain.

## Input

`InputReader` handles console input.

## Output

`ConsoleRenderer` handles console presentation.

## Configuration

`GameConfig` provides infrastructure configuration such as the `Scanner` bean.

The domain does not depend on console-specific classes.

---

# Persistence

The application uses Spring Data JPA and Hibernate.

The current development database is H2.

```properties
spring.datasource.url=jdbc:h2:mem:rpgdb
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
```

Persisted entities include:

* `Player`
* `Enemy`
* `Item`

The domain model is therefore mapped to persistence using JPA annotations while the domain behaviour remains inside the entities.

---

# Testing

The project uses:

* JUnit 5
* Mockito
* Spring Boot Test

The tests focus on domain behaviour and application integration.

Examples include:

* player damage calculation
* dead creature behaviour
* HP boundaries
* Spring application context loading

Example:

```java
@Test
@DisplayName("Health should never drop below zero")
void testHealthDoesNotGoNegative() {

    player.setStrength(999);

    player.attack(enemy);

    assertEquals(0, enemy.getHealthPoints());
}
```

The application context test uses `@MockitoBean` for interactive components:

```java
@MockitoBean
private InputReader inputReader;

@MockitoBean
private GameEngine gameEngine;
```

This allows the Spring context to be tested without starting the interactive console game.

---

# Technology Stack

| Technology               | Purpose                  |
| ------------------------ | ------------------------ |
| Java 21                  | Programming language     |
| Spring Boot 3.5          | Application framework    |
| Spring Data JPA          | Persistence              |
| Hibernate                | ORM                      |
| H2                       | Development database     |
| JUnit 5                  | Testing                  |
| Mockito                  | Mocking                  |
| Maven                    | Build management         |
| SpotBugs                 | Static analysis          |
| Docker                   | Containerization         |
| Docker Hub               | Container image registry |
| Azure Container Registry | Private Azure registry   |
| Azure Container Apps     | Cloud deployment         |
| GitHub Actions           | CI/CD                    |

---

# Docker

The application is containerized with Docker.

The image is published to Docker Hub and can also be used as the source image for Azure Container Registry.

Example:

```bash
docker pull <DOCKERHUB_USERNAME>/consolerpggame:latest
```

Run the application:

```bash
docker run --rm -it <DOCKERHUB_USERNAME>/consolerpggame:latest
```

The interactive terminal is required because the application reads commands from standard input.

---

# Azure Deployment

The project is prepared for deployment using Azure Container Registry and Azure Container Apps.

The deployment architecture is:

```text
GitHub
   |
   v
GitHub Actions
   |
   v
Docker Image
   |
   v
Azure Container Registry
   |
   v
Azure Container Apps
```

Azure Container Registry provides the private container image registry.

Azure Container Apps provides the managed container hosting environment.

The project therefore demonstrates the complete path from Java source code to a containerized Azure deployment.

---

# CI/CD

GitHub Actions automates the build and container publishing process.

Pipeline:

```text
Git Push / Pull Request
        |
        v
Checkout
        |
        v
Java 21
        |
        v
Maven clean verify
        |
        v
Tests
        |
        v
SpotBugs
        |
        v
Docker Build
        |
        v
Docker Hub
```

The pipeline runs for:

* `master`
* `main`

Docker Hub authentication uses GitHub Actions Secrets.

Required secrets:

```text
DOCKERHUB_USERNAME
DOCKERHUB_TOKEN
```

---

# Azure Configuration

The project also contains configuration for Azure services.

Azure credentials are provided through environment variables rather than hard-coded into the source code.

Example:

```properties
spring.cloud.azure.active-directory.credential.client-id=${AZURE_CLIENT_ID}
spring.cloud.azure.active-directory.profile.tenant-id=${AZURE_TENANT_ID}
```

This approach keeps sensitive configuration outside the source code.

---

# Running Locally

Requirements:

* Java 21
* Maven
* Git
* Docker (optional)

Clone the repository:

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd ConsoleRPGGame
```

Build:

```bash
mvn clean verify
```

Run:

```bash
mvn spring-boot:run
```

The game starts automatically through Spring Boot.

---

# Game Menu

```text
--- MAIN MENU ---

1 - Explore (Battle)
2 - View Character Profile
3 - Exit Game
```

`1` starts a battle.

`2` displays the character profile.

`3` terminates the game.

---

# DDD Concepts Demonstrated

This project intentionally demonstrates the following DDD concepts and principles:

| DDD Concept               | Project Implementation                            |
| ------------------------- | ------------------------------------------------- |
| Domain Model              | Creature, Player, Enemy, Item                     |
| Domain Behaviour          | attack(), takeDamage(), equipWeapon(), useItem()  |
| Entities                  | Player, Enemy, Item                               |
| Repository Abstraction    | PlayerRepository, EnemyRepository, ItemRepository |
| Domain Rules / Invariants | HP boundaries, attack rules, item restrictions    |
| Application Layer         | GameEngine, CombatService, InventoryService       |
| Factory                   | CreatureFactory                                   |
| Strategy Pattern          | AttackStrategy                                    |
| Separation of Concerns    | Domain / Application / Infrastructure             |
| Persistence Boundary      | Spring Data JPA repositories                      |

The project deliberately avoids placing the core game rules inside infrastructure code.

The objective is to keep the domain model responsible for the behaviour that belongs to the domain.

---

# Current DDD Scope

This project demonstrates practical DDD concepts at a small application scale.

It is not intended to claim a complete enterprise tactical DDD implementation.

Concepts such as explicit Value Objects, Domain Events, multiple Bounded Contexts and a more formal Aggregate architecture could be introduced as the domain grows.

This makes the project suitable as a foundation for demonstrating how the architecture can evolve from a small domain model toward a larger DDD-based system.

---

# Future Improvements

Potential extensions include:

* Value Objects such as `HealthPoints`, `Experience` and `Gold`
* explicit Aggregate Root design
* Domain Events
* stronger repository abstractions
* additional Bounded Contexts
* PostgreSQL
* Flyway migrations
* Azure Key Vault integration
* automated deployment from GitHub Actions to Azure
* REST API
* additional player classes
* additional enemy types
* quests
* shops
* NPCs
* persistent game sessions

---

# Author

Lukas Simek

Java Backend Developer Portfolio Project

The project was developed as a practical demonstration of Java, Spring Boot, Domain-Driven Design and modern cloud deployment practices.

# License

This project is intended as a learning and portfolio project.

# Console RPG Game

A **Domain-Driven Design (DDD)** based console RPG application built with **Java 21 and Spring Boot**.

The project was created as a portfolio demonstration of practical **DDD, object-oriented programming, layered architecture, design patterns, persistence, testing, Docker, CI/CD and Azure deployment**.

## DDD Architecture

The application is organized around the domain rather than technical infrastructure.

```text
application
├── GameEngine
├── CombatService
├── InventoryService
└── GameState

domain
├── creature
│   ├── Creature
│   ├── Player
│   └── Enemy
│
├── combat
│   ├── AttackStrategy
│   ├── MeleeAttack
│   └── MagicAttack
│
└── item
    ├── Item
    └── ItemType

infrastructure
├── config
├── input
└── output
```

## Domain-Driven Design

The core business rules are implemented inside the domain model.

Examples:

* dead creatures cannot attack
* dead creatures cannot receive additional damage
* health cannot become negative
* potions cannot exceed maximum HP
* only weapons can be equipped as weapons
* combat damage is calculated by a dedicated strategy

Main domain entities:

```text
Player
Enemy
Item
```

Domain behaviour includes:

```text
attack()
takeDamage()
isAlive()
equipWeapon()
unequipWeapon()
addItem()
removeItem()
useItem()
```

## Design Patterns

### Strategy Pattern

Combat calculation is separated using:

```text
AttackStrategy
├── MeleeAttack
└── MagicAttack
```

### Factory Pattern

`CreatureFactory` is responsible for creating players and enemies.

## Layered Architecture

The project separates:

```text
Domain
   ↓
Application
   ↓
Infrastructure
```

The domain contains business rules, the application layer coordinates use cases, and infrastructure handles technical concerns such as console I/O and persistence.

## Persistence

Spring Data JPA and Hibernate are used for persistence.

Development database:

```text
H2
```

Repositories:

```text
PlayerRepository
EnemyRepository
ItemRepository
```

## Testing

The project uses:

* JUnit 5
* Mockito
* Spring Boot Test

Tests cover combat behaviour, HP boundaries, death conditions and Spring context loading.

## Containerization

The application is packaged as a Docker image and published to Docker Hub.

```bash
docker pull <DOCKERHUB_USERNAME>/consolerpggame:latest
docker run --rm -it <DOCKERHUB_USERNAME>/consolerpggame:latest
```

## Azure

The project is prepared for container deployment using:

```text
Docker
   ↓
Azure Container Registry
   ↓
Azure Container Apps
```

## CI/CD

GitHub Actions performs:

```text
Build
  ↓
Test
  ↓
SpotBugs
  ↓
Docker Build
  ↓
Docker Hub
```

## Technology Stack

```text
Java 21
Spring Boot 3.5
Spring Data JPA
Hibernate
H2
JUnit 5
Mockito
Maven
SpotBugs
Docker
Docker Hub
Azure Container Registry
Azure Container Apps
GitHub Actions
```

## Run Locally

```bash
mvn clean verify
mvn spring-boot:run
```

The game starts automatically in the console.

## DDD Scope

The project demonstrates practical DDD fundamentals including domain entities, domain behaviour, repository abstraction, application services, factories, strategies and separation of domain from infrastructure.

It is intentionally kept as a small domain model and can be extended with Value Objects, Aggregate Roots, Domain Events and additional Bounded Contexts as the domain grows.

## Author

Lukas Simek

Java Backend Developer Portfolio Project

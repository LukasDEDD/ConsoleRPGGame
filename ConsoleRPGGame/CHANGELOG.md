# Changelog

All notable changes to this project are documented in this file.

## [1.0.0] - Initial Release

### Added

* Initial Domain-Driven Design based RPG domain model
* Player and Enemy entities
* Creature domain abstraction
* Combat system with `AttackStrategy`
* Melee and Magic attack strategies
* Inventory and item management
* Weapon and potion handling
* `CreatureFactory` for domain object creation
* Application services for combat and inventory
* Spring Data JPA repositories
* H2 database integration
* JUnit 5 and Mockito tests
* Spring Boot application context test
* Maven build configuration
* SpotBugs static code analysis
* Docker containerization
* Docker Hub image publishing
* GitHub Actions CI/CD pipeline
* Azure Container Registry deployment support
* Azure Container Apps deployment support
* Initial DDD-oriented layered architecture

### Architecture

* Domain layer separated from application and infrastructure layers
* Domain behaviour encapsulated inside domain entities
* Repository abstraction for persistence
* Strategy Pattern for combat behaviour
* Factory Pattern for creature creation
* Spring Dependency Injection for application components
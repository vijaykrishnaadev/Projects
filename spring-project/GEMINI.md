# Spring Project - E-commerce API

This project is a Spring Boot 3.x backend for an E-commerce API.

## Architecture
- **Layered Architecture:** Controller -> Service -> Repository -> Entity.
- **DTOs:** Use Data Transfer Objects for API requests and responses to avoid exposing internal entities.
- **Validation:** Use `@Valid` and JSR-303 annotations for request validation.

## Tech Stack
- **Java:** 25 (LTS)
- **Framework:** Spring Boot 3.2.3
- **Database:** H2 (In-memory)
- **Build Tool:** Maven

## Conventions
- Package structure: `com.ecommerce.api`
- Follow RESTful best practices for endpoint design.
- Use Lombok to reduce boilerplate (Constructor injection over `@Autowired`).

## Getting Started
1. Install Java 25 and Maven.
2. Run `./mvnw spring-boot:run`.
3. H2 Console is available at `http://localhost:8080/h2-console`.

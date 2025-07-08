## Java Snake Game

* **Description**: A score tracker for a Snake game, featuring a high score board that displays the top 10 scores overall or the user's personal top 10 scores.

### Tecnologies

1. Authentication with Spring Security
2. Frontend with Spring Web
3. Database with H2 and Spring JPA
4. Bootstrap and Thymeleaf
5. Validation
6. Spring Boot DevTools

## Entities

- users
    - id UUID
    - username String
    - password String encrypted
    - role Enum
    - email String

- scores
    - id UUID
    - score Integer
    - time double
    - createdAt Date
    - userId UUID

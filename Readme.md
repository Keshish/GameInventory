# Game Inventory Project

This project is a game inventory management system built using Java Spring Boot and Maven. It uses an H2 in-memory database and includes four tables for game inventory, along with a user table for authentication and authorization.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven

## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/game-inventory.git
    cd game-inventory
    ```

2. **Build the project using Maven:**
    ```bash
    mvn clean install
    ```

3. **Run the Spring Boot application:**
    ```bash
    java -jar target/game-inventory-1.0.0.jar
    ```

   Alternatively, you can use Maven:
    ```bash
    mvn spring-boot:run
    ```

4. **Access the application:**
   Open your web browser and go to [http://localhost:8080](http://localhost:8080)

## Database Initialization

The project uses Liquibase for database versioning. The initial data is inserted during application startup. You don't need to set up the database manually.

## Default Users

Two default users are available for testing:

- **Admin Role:**
    - Username: username1
    - Password: Admin

- **User Role:**
    - Username: username2
    - Password: user

## API Endpoints

- **Game Inventory API:** [http://localhost:8080/api/games](http://localhost:8080/api/games)
    - List all games: `GET /api/games`
    - Get a specific game: `GET /api/games/{id}`
    - Add a new game: `POST /api/games`
    - Update an existing game: `PUT /api/games/{id}`
    - Delete a game: `DELETE /api/games/{id}`

- **User Authentication API:** [http://localhost:8080/api/auth](http://localhost:8080/api/auth)
    - Login: `POST /api/auth/login`

## Testing

To run the tests, use the following Maven command:

```bash
mvn test

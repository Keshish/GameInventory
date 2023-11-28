# Game Inventory Project

This project is a game inventory management system built using Java Spring Boot and Maven. It uses an H2 in-memory database and includes four tables for game inventory, along with a user table for authentication and authorization.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven

## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/Keshish/GameInventory
    cd GameInventory
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
   Open your web browser and go to [http://localhost:7474](http://localhost:7474)

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

### Game Inventory API
- List all games: `GET /api/games/all`
- Create a new game: `POST /api/games`
- Update an existing game: `PUT /api/games/{id}`
- Delete a game: `DELETE /api/games/{id}`

### Inventory API
- List all inventories: `GET /api/inventories/all`

### Publisher API
- List all publishers: `GET /api/publishers/all`
- Get paginated list of publishers: `GET /api/publishers/paginated-publishers`

### User Authentication API
- Login: `POST /api/auth/login`



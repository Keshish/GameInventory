CREATE TABLE  publisher (
    publisher_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    founded_year INT
);

CREATE TABLE game (
    game_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    release_date VARCHAR(255),
    genre VARCHAR(255),
    platform VARCHAR(255),
    publisher_id BIGINT,
    FOREIGN KEY (publisher_id) REFERENCES publisher(publisher_id)
    );

CREATE TABLE  price (
    price_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    price_value DOUBLE,
    currency VARCHAR(255),
    game_id BIGINT,
    FOREIGN KEY (game_id) REFERENCES game(game_id)
    );

CREATE TABLE  inventory (
    inventory_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    game_id BIGINT,
    quantity INT,
    FOREIGN KEY(game_id)  REFERENCES game(game_id)
    );

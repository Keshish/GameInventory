CREATE TABLE publisher (
                           publisher_id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           founded_year INT NOT NULL
);

CREATE TABLE price (
                       price_id SERIAL PRIMARY KEY,
                       value DOUBLE NOT NULL,
                       currency VARCHAR(10) NOT NULL,
                       game_id BIGINT UNIQUE,
                       CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES game(game_id) ON DELETE CASCADE
);

CREATE TABLE inventory (
                           inventory_id SERIAL PRIMARY KEY,
                           game_id BIGINT NOT NULL,
                           quantity INT NOT NULL,
                           CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES game(game_id) ON DELETE CASCADE
);

CREATE TABLE game (
                      game_id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      release_date VARCHAR(10) NOT NULL,
                      genre VARCHAR(255) NOT NULL,
                      platform VARCHAR(255) NOT NULL,
                      price_id BIGINT,
                      publisher_id BIGINT,
                      CONSTRAINT fk_price FOREIGN KEY (price_id) REFERENCES price(price_id) ON DELETE SET NULL,
                      CONSTRAINT fk_publisher FOREIGN KEY (publisher_id) REFERENCES publisher(publisher_id) ON DELETE SET NULL
);

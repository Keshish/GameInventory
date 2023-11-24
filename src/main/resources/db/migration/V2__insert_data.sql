-- Mock data for the publisher table
INSERT INTO publisher (name, founded_year) VALUES
                                               ('Electronic Arts', 1982),
                                               ('Ubisoft', 1986),
                                               ('Activision', 1979),
                                               ('Nintendo', 1889),
                                               ('Sony Interactive Entertainment', 1993);

-- Mock data for the game table
INSERT INTO game (title, release_date, genre, platform, publisher_id) VALUES
                                                                          ('FIFA 22', '2021-10-01', 'Sports', 'PlayStation 5', 1),
                                                                          ('Assassin''s Creed Valhalla', '2020-11-10', 'Action-Adventure', 'Xbox Series X', 2),
                                                                          ('Call of Duty: Warzone', '2020-03-10', 'First-Person Shooter', 'PC', 3),
                                                                          ('The Legend of Zelda: Breath of the Wild', '2017-03-03', 'Action-Adventure', 'Nintendo Switch', 4),
                                                                          ('God of War', '2018-04-20', 'Action-Adventure', 'PlayStation 4', 5);

-- Mock data for the price table
INSERT INTO price (price_value, currency, game_id) VALUES
                                                       (59.99, 'USD', 1),
                                                       (49.99, 'USD', 2),
                                                       (0.00, 'USD', 3),
                                                       (39.99, 'USD', 4),
                                                       (59.99, 'USD', 5);

-- Mock data for the inventory table
INSERT INTO inventory (game_id, quantity) VALUES
                                              (1, 100),
                                              (2, 75),
                                              (3, 200),
                                              (4, 150),
                                              (5, 120);



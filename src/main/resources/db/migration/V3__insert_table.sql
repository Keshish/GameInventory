CREATE TABLE "users" (
                         id INTEGER NOT NULL PRIMARY KEY auto_increment,
                         username VARCHAR(100),
                         email VARCHAR(100),
                         CONSTRAINT username_unique UNIQUE (username)
);
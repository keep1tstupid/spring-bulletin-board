DROP TABLE IF EXISTS item;
CREATE TABLE item(
    id serial PRIMARY KEY, title VARCHAR(255),
    type VARCHAR(255), description VARCHAR(255),
    contactInfo VARCHAR(255)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user(
    id serial PRIMARY KEY, username VARCHAR(255),
    password VARCHAR(255), email VARCHAR(255),
    role VARCHAR(255)
);

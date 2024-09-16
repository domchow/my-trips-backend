DROP TABLE IF EXISTS trip;
DROP TABLE IF EXISTS users;
CREATE TABLE trip(id serial PRIMARY KEY, city VARCHAR(255), date DATE, description TEXT);
CREATE TABLE users(id serial PRIMARY KEY, name VARCHAR(255), password VARCHAR(255), roles VARCHAR(255), active BOOL);

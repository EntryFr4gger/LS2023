DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Token CASCADE;
DROP TABLE IF EXISTS Board CASCADE;
DROP TABLE IF EXISTS User_Board CASCADE;
DROP TABLE IF EXISTS List CASCADE;
DROP TABLE IF EXISTS Card CASCADE;

create table Users (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(60) NOT NULL,
    email   VARCHAR(320) UNIQUE NOT NULL CHECK ( email ~ '^[A-Za-z0-9+_.-]+@(.+)$')
);

create table Token (
    token   CHAR(36) PRIMARY KEY,
    user_id INT NOT NULL REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Board (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    description VARCHAR(1000)
);

create table User_Board (
    user_id     INT NOT NULL REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    board_id    INT NOT NULL REFERENCES Board (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table List (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(60) NOT NULL,
    board_id    INT NOT NULL REFERENCES Board (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Card (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(60) NOT NULL,
    description VARCHAR(1000),
    dueDate     CHAR(12) NOT NULL CHECK ( dueDate ~ '^(?:[01]\d|2[0123])\:(?:[012345]\d)\:(?:[012345]\d)\.\d{3}$'),
    board_id    INT NOT NULL REFERENCES Board (id) ON DELETE CASCADE ON UPDATE CASCADE,
    list_id     INT NOT NULL REFERENCES List (id) ON DELETE CASCADE ON UPDATE CASCADE
);

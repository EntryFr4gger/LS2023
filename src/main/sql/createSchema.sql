DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Tokens CASCADE;
DROP TABLE IF EXISTS Boards CASCADE;
DROP TABLE IF EXISTS User_Board CASCADE;
DROP TABLE IF EXISTS Lists CASCADE;
DROP TABLE IF EXISTS Cards CASCADE;

create table Users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(60)         NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL CHECK ( email ~ '^[A-Za-z0-9+_.-]+@(.+)$'
) ,
    password    VARCHAR(64) NOT NULL
);

create table Tokens
(
    token   CHAR(36) PRIMARY KEY,
    user_id INT NOT NULL REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Boards
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(30) UNIQUE NOT NULL,
    description VARCHAR(1000)      NOT NULL
);

create table User_Board
(
    user_id  INT NOT NULL REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    board_id INT NOT NULL REFERENCES Boards (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id, board_id)
);

create table Lists
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(60) NOT NULL,
    board_id INT         NOT NULL REFERENCES Boards (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Cards
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(60) NOT NULL,
    description VARCHAR(1000),
    dueDate     DATE,
    cix         INT,
    board_id    INT         NOT NULL REFERENCES Boards (id) ON DELETE CASCADE ON UPDATE CASCADE,
    list_id     INT REFERENCES Lists (id) ON DELETE CASCADE ON UPDATE CASCADE
);

BEGIN;
    INSERT INTO Users(name, email)
        VALUES ('Bernardo', 'bernardo@isel.pt'),
               ('Marcorio', 'marcorio@isel.pt'),
               ('Rafael', 'rafael@isel.pt');

    INSERT INTO Tokens (token, user_id)
        VALUES ('B123', 1),
               ('M123', 2),
               ('R123', 3);

    INSERT INTO Boards (name, description)
        VALUES ('UmaBoard', 'Esta é a board um'),
               ('OutraBoard', 'Esta é outra board');

    INSERT INTO User_Board (user_id, board_id)
        VALUES (1, 1),
               (2, 2),
               (3, 1);

    INSERT INTO  Lists (name, board_id)
        VALUES ('PrimeiraLista', 1),
               ('SegundaLista', 1),
               ('TerceiraLista', 2);

    INSERT INTO Cards (name, description, dueDate, board_id, list_id)
        VALUES ('LS', 'Phase1', '23:44:59.903', 1, 1),
               ('PC', 'Phase2', '23:44:59.903', 1, 1),
               ('SI', 'Phase3', '23:44:59.903', 1, 2),
               ('ALG', 'Phase4', '23:44:59.903', 2, 3),
               ('IASA', 'Phase4', '23:44:59.903', 2, 3),
               ('LAE', 'Phase4', '23:44:59.903', 2, 3);
COMMIT;

/*
insert into students(course, number, name) select cid as course, 12346 as number, 'Bob' as name from courses where name = 'LEIC'
 */
BEGIN;
INSERT INTO Users(name, email)
VALUES ('Bernardo', 'bernardo@isel.pt'),
       ('Marcorio', 'marcorio@isel.pt'),
       ('Rafael', 'rafael@isel.pt');

INSERT INTO Tokens (token, user_id)
VALUES ('9f1e3d11-8c18-4cd7-93fc-985c4794cfd9', 1),
       ('3b45e8dc-0315-4d4b-869b-5298b4db7242', 2),
       ('9f1e3d11-8c18-4cd7-93fc-985c4794cfd8', 3);

INSERT INTO Boards (name, description)
VALUES ('UmaBoard', 'Esta é a board um'),
       ('OutraBoard', 'Esta é outra board');

INSERT INTO User_Board (user_id, board_id)
VALUES (1, 1),
       (2, 2),
       (3, 1);

INSERT INTO Lists (name, board_id)
VALUES ('PrimeiraLista', 1),
       ('SegundaLista', 1),
       ('TerceiraLista', 2);

INSERT INTO Cards (name, description, dueDate, cix, board_id, list_id)
VALUES ('LS', 'Phase1', '2023-03-21', 1, 1, 1),
       ('PC', 'Phase2', '2023-03-21', 2, 1, 1),
       ('SI', 'Phase3', '2023-03-21', 1, 1, 2),
       ('ALG', 'Phase4', '2023-03-21', 1, 2, 3),
       ('IASA', 'Phase4', '2023-03-21', 2, 2, 3),
       ('LAE', 'Phase4', '2023-03-21', 3, 2, 3);
COMMIT;
ROLLBACK;
BEGIN;
INSERT INTO Users(name, email, password)
VALUES ('Bernardo', 'bernardo@isel.pt',
        'EDE898F11FBD617FC6306B19ACDDC9456CA7D4E36321BAC98A87531B6738BFAF'),                                            --Abcd1234&1
       ('Mary Johnson', 'mary.johnson@example.com',
        'EDE898F11FBD617FC6306B19ACDDC9456CA7D4E36321BAC98A87531B6738BFAF'),                                            --Abcd1234&1
       ('David Lee', 'david.lee@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),                                            --Abcd1234&
       ('Karen Wilson', 'karen.wilson@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Robert Miller', 'robert.miller@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Jennifer Jones', 'jennifer.jones@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('William Davis', 'william.davis@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Emily Martinez', 'emily.martinez@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Daniel Johnson', 'daniel.johnson@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Alexis Thompson', 'alexis.thompson@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Matthew Davis', 'matthew.davis@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Sophia Rodriguez', 'sophia.rodriguez@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Gabriel Hernandez', 'gabriel.hernandez@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Olivia Taylor', 'olivia.taylor@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Ethan Wilson', 'ethan.wilson@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Isabella Johnson', 'isabella.johnson@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Aiden Brown', 'aiden.brown@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Mia Davis', 'mia.davis@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Jackson Taylor', 'jackson.taylor@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Ava Martinez', 'ava.martinez@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Lucas Hernandez', 'lucas.hernandez@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Chloe Wilson', 'chloe.wilson@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Logan Johnson', 'logan.johnson@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Emma Martinez', 'emma.martinez@example.com',
        'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406'),--Abcd1234&
       ('Noah Davis', 'noah.davis@example.com', 'E5E06F3C000F53DDA3EDC68512EDBCF1581A74E1D5EC90605956D47499E18406');--Abcd1234&

INSERT INTO Tokens (token, user_id)
VALUES ('9f1e3d11-8c18-4cd7-93fc-985c4794cfd9', 1),
       ('f20c2db2-8f1a-4469-9d9c-dcbafab8a580', 2),
       ('893f8374-4688-4ed3-9cb4-4d285e696ca8', 3),
       ('3d3f3a60-7eaa-4e94-92d9-c9bc0c2f1411', 4),
       ('bf104b44-d7c8-48b1-9f9d-9fca1e10f38c', 5),
       ('aae7cb87-8178-4fb2-9a9d-7af0e1e67a7d', 6),
       ('7c6d0658-8a62-484f-a53d-b105bc24b8eb', 7),
       ('008f3925-d0d5-4978-bbe6-e34b60cfaef2', 8),
       ('8a7b6940-635d-422f-93d2-1782b95c1f3a', 9),
       ('07e7c019-797a-4ef6-9e11-e413b243fd32', 10),
       ('9f47434c-8fd6-462d-a07d-c81421e2c292', 11),
       ('1e22cf3b-ec32-43a6-a02d-f3b3e3bdc6a8', 12),
       ('051236ac-cd9c-4960-987c-b2d4fa596c4d', 13),
       ('c4f7d02d-9029-475a-bf6b-7f91ad1e1014', 14),
       ('4c7ecb46-cd03-48db-ba10-0b6d5e5c5f20', 15),
       ('b7d5ab9f-9f2b-44a5-94d5-d0a07e2a0e1f', 16),
       ('d3dd9400-8d96-432c-9597-19cb57b1ca43', 17),
       ('bbef7869-cdc9-4718-a0c2-29b87d9d9eb3', 18),
       ('3d1a9252-2b8b-42a8-8fc9-35b9e9c1e834', 19),
       ('d4067581-5188-41f8-991e-46e0d1c9928b', 20),
       ('0c0f34bb-5e6f-425e-b096-7dc9bf1a6a2e', 21),
       ('c786bb69-2676-4bb5-8be5-5a266a40c6dc', 22),
       ('4c4c539e-4c04-4e01-9d25-f47d59f22156', 23),
       ('b399a90a-89f1-49d7-95a8-cf56d7f9f768', 24),
       ('eae6a17b-07b1-4ce7-bd14-61f2d9a233e6', 25);

INSERT INTO Boards (name, description)
VALUES ('CivicBoard', 'Board about the Honda Civic'),
       ('MustangBoard', 'Board about the Ford Mustang'),
       ('CorvetteBoard', 'Board about the Chevrolet Corvette'),
       ('ChargerBoard', 'Board about the Dodge Charger'),
       ('CamaroBoard', 'Board about the Chevrolet Camaro'),
       ('F150Board', 'Board about the Ford F-150'),
       ('WranglerBoard', 'Board about the Jeep Wrangler'),
       ('AccordBoard', 'Board about the Honda Accord'),
       ('BroncoBoard', 'Board about the Ford Bronco'),
       ('CherokeeBoard', 'Board about the Jeep Cherokee'),
       ('ChallengerBoard', 'Board about the Dodge Challenger'),
       ('EscapeBoard', 'Board about the Ford Escape'),
       ('FocusBoard', 'Board about the Ford Focus'),
       ('MalibuBoard', 'Board about the Chevrolet Malibu'),
       ('ExplorerBoard', 'Board about the Ford Explorer'),
       ('RamBoard', 'Board about the Ram truck'),
       ('TacomaBoard', 'Board about the Toyota Tacoma'),
       ('TundraBoard', 'Board about the Toyota Tundra'),
       ('GTRBoard', 'Board about the Nissan GTR'),
       ('370ZBoard', 'Board about the Nissan 370Z'),
       ('LeafBoard', 'Board about the Nissan Leaf'),
       ('PathfinderBoard', 'Board about the Nissan Pathfinder'),
       ('RogueBoard', 'Board about the Nissan Rogue'),
       ('AltimaBoard', 'Board about the Nissan Altima'),
       ('ArmadaBoard', 'Board about the Nissan Armada'),
       ('SentraBoard', 'Board about the Nissan Sentra'),
       ('TitanBoard', 'Board about the Nissan Titan'),
       ('MaximaBoard', 'Board about the Nissan Maxima'),
       ('MicraBoard', 'Board about the Nissan Micra'),
       ('VersaBoard', 'Board about the Nissan Versa'),
       ('XterraBoard', 'Board about the Nissan Xterra'),
       ('FrontierBoard', 'Board about the Nissan Frontier'),
       ('QashqaiBoard', 'Board about the Nissan Qashqai'),
       ('Rav4Board', 'Board about the Toyota RAV4'),
       ('CamryBoard', 'Board about the Toyota Camry'),
       ('SiennaBoard', 'Board about the Toyota Sienna'),
       ('AvalonBoard', 'Board about the Toyota Avalon'),
       ('HighlanderBoard', 'Board about the Toyota Highlander'),
       ('4RunnerBoard', 'Board about the Toyota 4Runner'),
       ('YarisBoard', 'Board about the Toyota Yaris'),
       ('SupraBoard', 'Board about the Toyota Supra'),
       ('LandCruiserBoard', 'Board about the Toyota Land Cruiser'),
       ('PriusBoard', 'Board about the Toyota Prius'),
       ('TacomaBoard2', 'Board about the Toyota Tacoma'),
       ('TundraBoard1', 'Board about the Toyota Tundra'),
       ('MiraiBoard', 'Board about the Toyota Mirai'),
       ('VenzaBoard', 'Board about the Toyota Venza'),
       ('C-HRBoard', 'Board about the Toyota C-HR'),
       ('MatrixBoard', 'Board about the Toyota Matrix'),
       ('EchoBoard', 'Board about the Toyota Echo'),
       ('SolaraBoard', 'Board about the Toyota Solara'),
       ('FJBoard', 'Board about the Toyota FJ Cruiser'),
       ('MR2Board', 'Board about the Toyota MR2'),
       ('FerrariBoard', 'A board for Ferrari fans'),
       ('LamborghiniBoard', 'A board for Lamborghini fans'),
       ('PorscheBoard', 'A board for Porsche fans'),
       ('BugattiBoard', 'A board for Bugatti fans'),
       ('McLarenBoard', 'A board for McLaren fans'),
       ('MercedesBoard', 'A board for Mercedes fans'),
       ('BMWBoard', 'A board for BMW fans'),
       ('AudiBoard', 'A board for Audi fans'),
       ('FordBoard', 'A board for Ford fans'),
       ('ChevroletBoard', 'A board for Chevrolet fans'),
       ('DodgeBoard', 'A board for Dodge fans'),
       ('TeslaBoard', 'A board for Tesla fans'),
       ('MaseratiBoard', 'A board for Maserati fans'),
       ('BentleyBoard', 'A board for Bentley fans'),
       ('RollsRoyceBoard', 'A board for Rolls-Royce fans'),
       ('CadillacBoard', 'A board for Cadillac fans'),
       ('JeepBoard', 'A board for Jeep fans'),
       ('LandRoverBoard', 'A board for Land Rover fans'),
       ('ToyotaBoard', 'A board for Toyota fans'),
       ('HondaBoard', 'A board for Honda fans'),
       ('NissanBoard', 'A board for Nissan fans'),
       ('MitsubishiBoard', 'A board for Mitsubishi fans'),
       ('SubaruBoard', 'A board for Subaru fans'),
       ('KiaBoard', 'A board for Kia fans'),
       ('HyundaiBoard', 'A board for Hyundai fans'),
       ('VolvoBoard', 'A board for Volvo fans'),
       ('VolkswagenBoard', 'A board for Volkswagen fans'),
       ('FiatBoard', 'A board for Fiat fans'),
       ('AlfaRomeoBoard', 'A board for Alfa Romeo fans'),
       ('LanciaBoard', 'A board for Lancia fans'),
       ('AcuraBoard', 'A board for Acura fans'),
       ('InfinitiBoard', 'A board for Infiniti fans'),
       ('LexusBoard', 'A board for Lexus fans'),
       ('JaguarBoard', 'A board for Jaguar fans'),
       ('LotusBoard', 'A board for Lotus fans'),
       ('MiniBoard', 'A board for Mini fans'),
       ('PeugeotBoard', 'A board for Peugeot fans'),
       ('RenaultBoard', 'A board for Renault fans'),
       ('SeatBoard', 'A board for Seat fans'),
       ('SkodaBoard', 'A board for Skoda fans'),
       ('SuzukiBoard', 'A board for Suzuki fans'),
       ('SmartBoard', 'A board for Smart fans'),
       ('OpelBoard', 'A board for Opel fans'),
       ('CitroenBoard', 'A board for Citroen fans'),
       ('FiatBoard1', 'A board for Fiat fans'),
       ('MustangFans', 'Discussões sobre o icônico carro esportivo da Ford'),
       ('VolkswagenLovers', 'Comunidade de entusiastas dos carros da Volkswagen');

INSERT INTO User_Board (user_id, board_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15),
       (1, 16),
       (1, 17),
       (1, 18),
       (1, 19),
       (1, 20),
       (1, 21),
       (1, 22),
       (1, 23),
       (1, 24),
       (1, 25),
       (1, 26),
       (1, 27),
       (1, 28),
       (1, 29),
       (1, 30),
       (1, 31),
       (1, 32),
       (1, 33),
       (1, 34),
       (1, 35),
       (1, 36),
       (1, 37),
       (1, 38),
       (1, 39),
       (1, 40),
       (1, 41),
       (1, 42),
       (1, 43),
       (1, 44),
       (1, 45),
       (1, 46),
       (1, 47),
       (1, 48),
       (1, 49),
       (1, 50),
       (2, 27),
       (3, 28),
       (4, 29),
       (5, 30),
       (6, 31),
       (7, 32),
       (8, 33),
       (9, 34),
       (10, 35),
       (11, 36),
       (12, 37),
       (13, 38),
       (14, 39),
       (15, 40),
       (16, 41),
       (17, 42),
       (18, 43),
       (19, 44),
       (20, 45),
       (21, 46),
       (22, 47),
       (23, 48),
       (24, 49),
       (25, 50);



INSERT INTO Lists (name, board_id)
SELECT 'List ' || generate_series(1, 40) AS name,
       board_id
FROM (SELECT CASE
                 WHEN id IN (1, 3, 9) THEN id
                 ELSE floor(random() * 100) + 1
                 END AS board_id
      FROM generate_series(1, 100) t(id)) b;

INSERT INTO Cards (name, description, dueDate, cix, board_id, list_id)
SELECT CASE
           WHEN row_number <= 1 THEN 'Project 1'
           ELSE 'Project ' || row_number
           END                 AS name,
       'This is the description for ' || CASE
                                             WHEN row_number <= 1 THEN 'm'
                                             ELSE 'card ' || row_number
           END                 AS description,
       '2023-03-21'            AS dueDate,
       floor(random() * 5) + 1 AS cix,
       CASE
           WHEN row_number <= 25 THEN 5
           WHEN row_number <= 50 THEN 3
           WHEN row_number <= 75 THEN 9
           ELSE floor(random() * 100) + 1
           END                 AS board_id,
       CASE
           WHEN row_number <= 25 THEN floor(random() * 25) + 1
           WHEN row_number <= 50 THEN floor(random() * 25) + 26
           WHEN row_number <= 75 THEN floor(random() * 25) + 51
           ELSE floor(random() * 100) + 1
           END                 AS list_id
FROM generate_series(1, 500) t(row_number);

COMMIT;
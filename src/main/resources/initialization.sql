
DROP TABLE IF EXISTS "users";
CREATE TABLE IF NOT EXISTS "users"
(
    id     bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name   VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    phoneNumber     VARCHAR(255) NOT NULL
);
INSERT INTO "users" (id, name, address, phoneNumber)
VALUES (11, 'Ivawen', 'address', 'phoneNumber' );
INSERT INTO "users" (id, name, address, phoneNumber)
VALUES (10, 'Ivqqqan', 'Ivanov', 'Ivanov' );
INSERT INTO "users" (id, name, address, phoneNumber)
VALUES (12, 'ffff', 'Ivanov', 'Ivanov' );
INSERT INTO "users" (id, name, address, phoneNumber)
VALUES (14, 'ffff', 'Ivanov', 'Ivanov' );

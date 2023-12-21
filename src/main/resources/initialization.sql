
DROP TABLE IF EXISTS "users";
CREATE TABLE IF NOT EXISTS "users"
(
    id     bigint PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    phoneNumber     VARCHAR(255) NOT NULL
);
-- INSERT INTO "users" (id, name, address, phoneNumber)
-- VALUES (1, 'Ivawen', 'address', 'phoneNumber' );
-- INSERT INTO "users" (id, name, address, phoneNumber)
-- VALUES (0, 'Ivqqqan', 'Ivanov', 'Ivanov' );
-- INSERT INTO "users" (id, name, address, phoneNumber)
-- VALUES (2, 'ffff', 'Ivanov', 'Ivanov' );
INSERT INTO "users" (id, name, address, phoneNumber)
VALUES (CAST(1 AS BIGINT), 'ffff', 'Ivanov', 'Ivanov' );

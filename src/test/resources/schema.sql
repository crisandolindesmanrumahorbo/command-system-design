CREATE TABLE IF NOT EXISTS todo
(
    id
    SERIAL
    PRIMARY
    KEY,
    title
    VARCHAR
(
    255
), is_completed BOOLEAN NOT NULL);

CREATE TABLE messages (
    id   SERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    text VARCHAR(256) NOT NULL
);

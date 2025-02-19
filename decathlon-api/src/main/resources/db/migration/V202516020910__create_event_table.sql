CREATE TABLE IF NOT EXISTS decathlon.events
(
    id         bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    event_type varchar NOT NULL,
    points     bigint  NOT NULL
);
--liquibase formatted sql

--changeset Nikolay:1
CREATE TABLE notification_task
(
    id       INT,
    chatId   INT,
    message  CHARACTER,
    timeDate CHARACTER
);

--changeset sbukaevsky:2
CREATE TABLE users
(
    id        BIGINT,
    user_name TEXT,
    user_id   BIGINT,
    phone     TEXT
);

--changeset olgakargan:3
CREATE TABLE pet
(
    id              BIGINT,
    breed           TEXT,
    pet_name        TEXT,
    year_of_birth   INT,
    description     TEXT
)
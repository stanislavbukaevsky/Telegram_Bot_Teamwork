--liquibase formatted sql

--changeset olgakargan:1
CREATE TABLE notification_task
(
    id       INT,
    chatId   INT,
    message  CHARACTER,
    timeDate CHARACTER
)

--changeset sbukaevsky:2
DROP TABLE notification_task;

--changeset sbukaevsky:3
CREATE TABLE users
(
    id        SERIAL,
    user_name TEXT,
    user_id   BIGINT,
    phone     TEXT
);


--changeset sbukaevsky:8
DROP TABLE users;

--changeset sbukaevsky:9
ALTER TABLE users ALTER COLUMN id TYPE BIGINT;
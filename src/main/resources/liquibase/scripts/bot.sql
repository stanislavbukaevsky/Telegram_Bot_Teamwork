--liquibase formatted sql

--changeset Nikolay:1
CREATE TABLE notification_task
(
    id       INT,
    chatId   INT,
    message  CHARACTER,
    timeDate CHARACTER
);


--changeset olgakargan:2
create table reportData

(
    id          integer PRIMARY KEY not null,
    chat_id     integer             not null,
    ration      varchar             not null,
    health      varchar             not null,
    habits      varchar             not null,
    filePath    varchar             not null,
    days        integer             not null,
    person_id   integer             not null,
    caption     varchar             not null,
    lastMessage date                not null,
    data        bytea               not null
);

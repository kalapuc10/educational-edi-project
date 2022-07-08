--liquibase formatted sql
CREATE TABLE main.user (
    id integer PRIMARY KEY,
    name varchar(256),
    login varchar(50) UNIQUE,
    email varchar UNIQUE
);
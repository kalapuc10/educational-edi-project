--liquibase formatted sql
CREATE TABLE main.role (
    id integer PRIMARY KEY,
    code varchar(20) UNIQUE
);
--liquibase formatted sql
CREATE TABLE main.document (
    id integer PRIMARY KEY,
    number varchar(40) UNIQUE,
    user_id integer NULL,
    FOREIGN KEY (user_id) REFERENCES main.user (id)
)
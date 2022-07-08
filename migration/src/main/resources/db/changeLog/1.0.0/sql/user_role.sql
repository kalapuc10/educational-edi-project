--liquibase formatted sql
CREATE TABLE main.user_role (
    user_id integer,
    role_id integer,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES main.user (id),
    FOREIGN KEY (role_id) REFERENCES main.role (id)
)
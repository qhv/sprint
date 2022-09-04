-- liquibase formatted sql

-- changeset alex:1
ALTER TABLE users
    ADD COLUMN image VARCHAR(128);

-- changeset alex:2
ALTER TABLE users_aud
    ADD COLUMN image VARCHAR(128);
CREATE TABLE roles
(
    id          serial primary key,
    name        varchar(50)
);

CREATE TABLE users
(
    id          bigserial primary key,
    username    varchar(255),
    email       varchar(255),
    password    varchar(255),
    social      varchar(100),
    register_at timestamp default current_timestamp,
    visited_at  timestamp default current_timestamp,
    role_id     integer
        constraint user_table_role_table_id_fk
            references roles
);

CREATE
    unique index user_table_login_uindex
    on users (email);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');


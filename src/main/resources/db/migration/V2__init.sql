CREATE TABLE tags
(
    id          bigserial primary key,
    name        varchar(255)
);

CREATE TABLE tasks
(
    id                  serial primary key,
    description         varchar(255),
    answer              varchar(255),
    tags                integer constraint task_table_tag_table_id_fk references tags,
    user_id             integer constraint task_table_user_table_id_fk references users
);

DROP table if exists tasks;
CREATE TABLE tasks
(
    id                  serial primary key,
    description         varchar(255),
    answer              varchar(255),
    tags                integer references tags(id),
    user_id             integer constraint task_table_user_table_id_fk references users
);
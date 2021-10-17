CREATE table users_solved
(
    id_task             integer,
    id_user             integer,
    primary key (id_task, id_user),
    foreign key (id_task) references tasks (id),
    foreign key (id_user) references users (id)
);

ALTER TABLE users
    ADD COLUMN solvedTasks integer constraint user_table_task_table_id_fk
        references tasks;

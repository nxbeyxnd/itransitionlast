CREATE TABLE tasks_tag
(
    id_task             integer,
    id_tag              integer,
    primary key (id_task, id_tag),
    foreign key (id_task) references tasks (id),
    foreign key (id_tag) references tags (id)
);
create table users
(
    id              int auto_increment,
    first_name      varchar(255)                       not null,
    last_name       varchar(255)                       not null,
    privilege_level enum ('ADMINISTRATOR', 'OPERATOR') not null,
    username        varchar(255)                       not null,
    password        varchar(255)                       null,
    version         tinyint default 1                  not null,

    constraint table_name_pk
        primary key (id)

);
create unique index users_username_uindex
    on users (username);

INSERT INTO users (id, first_name, last_name, privilege_level, username, password, version)
VALUES (1, 'Stefan', 'Antic', 'ADMINISTRATOR', 'stefanantic', '$2y$06$bYakIZG3C0N7bMA6yHTIdeK5edbl22cdtqpG7kZZ3VteDuK.jNMaC', 1);
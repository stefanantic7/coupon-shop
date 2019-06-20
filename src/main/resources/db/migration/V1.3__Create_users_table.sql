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


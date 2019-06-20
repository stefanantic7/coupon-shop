create table shops
(
    id      int auto_increment,
    name    varchar(255)      not null,
    version tinyint default 1 not null,

    constraint shops_pk
        primary key (id)
);


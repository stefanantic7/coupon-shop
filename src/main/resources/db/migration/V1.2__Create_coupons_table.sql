create table coupons
(
    id               int auto_increment,
    shop_id          int               not null,
    product          varchar(255)      not null,
    discounted_price float             not null,
    original_price   float             not null,
    valid_from       timestamp         not null,
    valid_to         timestamp         null,
    version          tinyint default 1 not null,

    constraint table_name_pk
        primary key (id)

);


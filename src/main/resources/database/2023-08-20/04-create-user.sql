--liquibase formatted sql
--changeset czuk102:4
create table user
(
    id       bigint auto_increment primary key,
    username     varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255) not null,
    role     varchar(255) not null
);

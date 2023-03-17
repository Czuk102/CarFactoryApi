--liquibase formatted sql
-- changeset czuk102:1
create table car (
                       id bigint not null auto_increment primary key,
                       engine varchar(255) default null,
                       engine_capacity double default null,
                       name varchar(255) default null,
                       price double default null
);


--liquibase formatted sql
-- changeset czuk102:2
create table equipment
(
    id     bigint auto_increment primary key,
    name   varchar(255) null,
    price  double       null,
    car_id bigint       null,
    constraint
        foreign key (car_id) references cars.car (id)
);


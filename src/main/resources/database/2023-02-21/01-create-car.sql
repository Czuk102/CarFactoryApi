--liquibase formatted sql
-- changeset czuk102:1
CREATE TABLE CAR (
                       ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       ENGINE VARCHAR(255) DEFAULT NULL,
                       ENGINE_CAPACITY DOUBLE DEFAULT NULL,
                       NAME VARCHAR(255) DEFAULT NULL,
                       PRICE DOUBLE DEFAULT NULL
);


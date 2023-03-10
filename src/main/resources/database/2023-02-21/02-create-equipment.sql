--liquibase formatted sql
-- changeset czuk102:2
CREATE TABLE EQUIPMENT
(
    ID     BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME   VARCHAR(255) NULL,
    PRICE  DOUBLE       NULL,
    CAR_ID BIGINT       NULL,
    CONSTRAINT
        FOREIGN KEY (CAR_ID) REFERENCES CARS.CAR (ID)
);


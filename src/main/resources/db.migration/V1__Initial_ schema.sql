CREATE TABLE location
(
    id           SERIAL PRIMARY KEY NOT NULL,
    city         varchar(255) NOT NULL,
    district     varchar(255) NOT NULL,
    town         varchar(255) NOT NULL,
    neighborhood varchar(255) NOT NULL,
    zip_code     varchar(255) NOT NULL
);



-- COPY location(city,district,town,neighborhood,zip_code)
--     FROM 'C:\Program Files\PostgreSQL\15\scripts\location.csv'
--     DELIMITER ';'
--     CSV HEADER;
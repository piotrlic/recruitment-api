create table users (
                       id int not null,
                       login VARCHAR(50) NOT NULL,
                       password_hash VARCHAR(50) NOT NULL,
                       creation_date DATE
);

INSERT INTO USERS (id, login, password_hash, creation_date) VALUES (1, 'piotrek', 'xddd', '2022-04-22');
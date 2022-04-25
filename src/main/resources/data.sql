CREATE TABLE USERS (
                       ID bigint auto_increment primary key,
                       LOGIN VARCHAR(50) NOT NULL,
                       PASSWORD_HASH VARCHAR(50) NOT NULL,
                       CREATION_DATE DATE
);

INSERT INTO USERS (LOGIN, PASSWORD_HASH, CREATION_DATE) VALUES ('piotrek', 'xddd', '2022-04-22');
INSERT INTO USERS (LOGIN, PASSWORD_HASH, CREATION_DATE) VALUES ('pawel', 'xddd', '2022-04-22');

CREATE TABLE JOB_OFFERS (
                    ID bigint auto_increment primary key,
                    USER_ID int NOT NULL,
                    JOB_CATEGORY VARCHAR(50) NOT NULL,
                    START_DATE DATE,
                    END_DATE DATE,
                    CONSTRAINT FK_USERS_JOB_OFFERS FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

--ALTER TABLE JOB_OFFERS ADD CONSTRAINT job_offers_id_unique UNIQUE(id);
--ALTER TABLE USERS ADD CONSTRAINT job_offers_id_unique UNIQUE(id);

insert into job_offers (USER_ID, JOB_CATEGORY, START_DATE, END_DATE) values (1, 'IT','2022-02-01','2022-05-01');
insert into job_offers (USER_ID, JOB_CATEGORY, START_DATE, END_DATE) values (1, 'FOOD_AND_DRINKS','2022-02-01','2022-05-01');
insert into job_offers (USER_ID, JOB_CATEGORY, START_DATE, END_DATE) values (2, 'SHOP_ASSISTANT','2022-02-01','2022-05-01');
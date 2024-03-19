DROP TABLE IF EXISTS customer_coach;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS gym;

CREATE TABLE customer
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_name varchar(255) NOT NULL
);

CREATE TABLE gym
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    gym_name varchar(255),
    city     varchar(255) NOT NULL
);

--Many-to-One
CREATE TABLE coach
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    coach_name varchar(255) NOT NULL,
    sport_type varchar(255) NOT NULL,
    gym_id     BIGINT,
    CONSTRAINT fk_gym_where_coach_work FOREIGN KEY (gym_id) REFERENCES gym (id)
);

--Many-to-Many
CREATE TABLE customer_coach
(
    customer_id BIGINT,
    coach_id    BIGINT,
    CONSTRAINT customer_coach_pk PRIMARY KEY (customer_id, coach_id),
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT coach_id_fk FOREIGN KEY (coach_id) REFERENCES coach (id) ON DELETE CASCADE ON UPDATE CASCADE
);
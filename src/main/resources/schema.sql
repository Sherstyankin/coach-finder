CREATE TABLE IF NOT EXISTS customer (
        id long GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        customer_name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS sports_complex (
        id long GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        sports_complex_name varchar(255),
        city NOT NULL
);

CREATE TABLE IF NOT EXISTS coach (
        id long GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        coach_name varchar(255) NOT NULL,
        sport_type varchar(255) NOT NULL,
        sports_complex_id long,
        CONSTRAINT fk_coach_to_sports_complex FOREIGN KEY(sports_complex_id) REFERENCES sports_complex(id)

);

CREATE TABLE IF NOT EXISTS customer_coach (
    customer_id long,
	coach_id long,
	CONSTRAINT customer_coach_pk PRIMARY KEY (customer_id, coach_id),
	CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT coach_id_fk FOREIGN KEY (coach_id) REFERENCES coach(id) ON DELETE CASCADE ON UPDATE CASCADE
);
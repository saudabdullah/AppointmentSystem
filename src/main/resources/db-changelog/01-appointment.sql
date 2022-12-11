CREATE TABLE appointment (
 id character varying(255) NOT NULL DEFAULT gen_random_uuid(),
 doctor_name VARCHAR(255),
 appointment_date VARCHAR(255),
 stauts VARCHAR(255),
 CONSTRAINT pk_appointment PRIMARY KEY (id)
);
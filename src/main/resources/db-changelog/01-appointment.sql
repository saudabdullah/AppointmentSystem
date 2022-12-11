CREATE TABLE appointment (
 id character varying(255) NOT NULL DEFAULT gen_random_uuid(),
 doctor_name VARCHAR(255),
 appointment_date DATE,
 stauts VARCHAR(255),
 CONSTRAINT pk_patient PRIMARY KEY (id)
);
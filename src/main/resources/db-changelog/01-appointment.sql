CREATE TABLE appointment (
 id character varying(255) NOT NULL DEFAULT gen_random_uuid(),
 doctor_name VARCHAR(255),
 appointment_date DATE,
 stauts VARCHAR(255),
 patient_id VARCHAR(255) ,
 CONSTRAINT pk_appointment PRIMARY KEY (id)
);

ALTER TABLE appointment ADD CONSTRAINT FK_PATIENT_APPOINTMENTS FOREIGN KEY (patient_id) REFERENCES patient (id);
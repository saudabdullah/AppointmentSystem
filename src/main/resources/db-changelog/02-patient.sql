CREATE TABLE IF NOT EXISTS patient
(
    id VARCHAR(255)  NOT NULL DEFAULT gen_random_uuid(),
    patient_name VARCHAR(255) ,
    birth_date DATE ,);

ALTER TABLE patient ADD CONSTRAINT FK_PATIENT_APPOINTMENTS FOREIGN KEY (appointments_id) REFERENCES appointment (id);
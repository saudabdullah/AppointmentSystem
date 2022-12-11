CREATE TABLE IF NOT EXISTS patient
(
    id VARCHAR(255)  NOT NULL DEFAULT gen_random_uuid(),
    patient_name VARCHAR(255) ,
    birth_date DATE ,
    CONSTRAINT pk_patient PRIMARY KEY (id)
 );


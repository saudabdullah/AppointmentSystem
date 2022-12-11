package com.clinic.appointmentSystem.repositories;

import com.clinic.appointmentSystem.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Patient getBypatientName(String patientName);
}

package com.clinic.appointmentSystem.repositories;

import com.clinic.appointmentSystem.entites.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("SELECT t from Appointment t where t.appointmentDate=:date")
    List<Appointment> findBydate(LocalDate date);
}

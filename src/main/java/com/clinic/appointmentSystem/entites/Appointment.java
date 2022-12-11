package com.clinic.appointmentSystem.entites;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "appointment")
public class Appointment {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name = "appointment_date")
    private LocalDate appointmentDate ;
    @Column(name = "stauts")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}

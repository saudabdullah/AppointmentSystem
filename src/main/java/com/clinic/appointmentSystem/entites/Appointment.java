package com.clinic.appointmentSystem.entites;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    private   String appointmentDate ;
    @Column(name = "stauts")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointments_id")
    private Patient patient;
}

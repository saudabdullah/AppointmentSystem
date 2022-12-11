package com.clinic.appointmentSystem.entites;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "patient")
public class Patient {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "birth_date")
    private String birthDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointments_id")
    private Set<Appointment> appointments;
}

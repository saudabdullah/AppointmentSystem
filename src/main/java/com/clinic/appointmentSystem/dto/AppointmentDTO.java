package com.clinic.appointmentSystem.dto;

import lombok.Data;

@Data
public class AppointmentDTO {
    private String doctorName;
    private String appointmentDate ;
    private String status;
}

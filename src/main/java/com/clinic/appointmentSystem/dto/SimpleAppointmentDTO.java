package com.clinic.appointmentSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class SimpleAppointmentDTO {
    List<AppointmentDTO> values;
    long pageCount;
    long count;
    Integer page;
    Integer size;
}

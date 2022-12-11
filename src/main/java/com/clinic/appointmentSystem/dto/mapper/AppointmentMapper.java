package com.clinic.appointmentSystem.dto.mapper;

import com.clinic.appointmentSystem.dto.AppointmentDTO;
import com.clinic.appointmentSystem.entites.Appointment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "Spring")
@Component
public interface AppointmentMapper {
    Appointment mapToAppointment(AppointmentDTO appointmentDTO);

    AppointmentDTO mapToAppointmentDTO(Appointment appointment);
}

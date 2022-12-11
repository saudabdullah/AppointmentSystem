package com.clinic.appointmentSystem.services;

import com.clinic.appointmentSystem.dto.AppointmentDTO;
import com.clinic.appointmentSystem.dto.mapper.AppointmentMapper;
import com.clinic.appointmentSystem.entites.Appointment;
import com.clinic.appointmentSystem.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentSerivce {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    public AppointmentDTO saveAppointment(UUID patientId, AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.mapToAppointment(appointmentDTO);
        appointment.getPatient().setId(patientId);
        appointment.setStatus("NEW");
        appointment = appointmentRepository.save(appointment);
        return appointmentMapper.mapToAppointmentDTO(appointment);
    }

    public List<AppointmentDTO> getTodayAppointmets() {
        List<Appointment> appointmentList = appointmentRepository.findBydate(LocalDate.now());
    }
}

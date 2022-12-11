package com.clinic.appointmentSystem.services;

import com.clinic.appointmentSystem.dto.AppointmentDTO;
import com.clinic.appointmentSystem.dto.SimpleAppointmentDTO;
import com.clinic.appointmentSystem.dto.mapper.AppointmentMapper;
import com.clinic.appointmentSystem.entites.Appointment;
import com.clinic.appointmentSystem.entites.Patient;
import com.clinic.appointmentSystem.repositories.AppointmentRepository;
import com.clinic.appointmentSystem.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AppointmentSerivce {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;

    public AppointmentDTO saveAppointment(UUID patientId, AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.mapToAppointment(appointmentDTO);
        appointment.getPatient().setId(patientId);
        appointment.setStatus("NEW");
        appointment = appointmentRepository.save(appointment);
        log.info("appointment saved successfully to DB");
        return appointmentMapper.mapToAppointmentDTO(appointment);
    }

    public List<AppointmentDTO> getTodayAppointmets() {
        List<Appointment> appointmentList = appointmentRepository.findBydate(LocalDate.now());
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for(Appointment appointment:appointmentList){
            AppointmentDTO appointmentDTO = appointmentMapper.mapToAppointmentDTO(appointment);
            appointmentDTOList.add(appointmentDTO);
        }
        return appointmentDTOList;
    }

    public void deleteAppointment(UUID appointmentId,String reason) {
        appointmentRepository.deleteById(appointmentId);
        log.info("appointment deleted from DB"+reason);
    }

    public List<AppointmentDTO> getAppointmentHistory(UUID patientId) {
        List<Appointment> appointmentList = appointmentRepository.findByPatientIdList(patientId);
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for(Appointment appointment:appointmentList){
            AppointmentDTO appointmentDTO = appointmentMapper.mapToAppointmentDTO(appointment);
            appointmentDTOList.add(appointmentDTO);
        }
        return appointmentDTOList;    }

    public SimpleAppointmentDTO filterAppointments(UUID patientID, LocalDate appointmentDate,
                                                   String sortBy, int page, int size, String direction) {
        SimpleAppointmentDTO simpleAppointmentDTO = new SimpleAppointmentDTO();
        List<String> sortByList = fixSortBy(sortBy);
        List<Sort.Order> sortByOrders = sortByList.stream().map(oe -> new Sort.Order(Sort.Direction.fromString(direction), oe))
                .collect(Collectors.toList());
        Page<Appointment> appointments = appointmentRepository.getfilteredAppointments(patientID,appointmentDate,
                PageRequest.of(page,size,Sort.by(sortByOrders)));
        simpleAppointmentDTO.setValues(appointmentMapper.mapToAppointmentDTOList(appointments.getContent()));
        simpleAppointmentDTO.setPageCount(appointments.getTotalPages());
        simpleAppointmentDTO.setCount(appointments.getTotalElements());
        simpleAppointmentDTO.setPage(page);
        simpleAppointmentDTO.setSize(size);
        return simpleAppointmentDTO;
    }

    private List<String> fixSortBy(String sortBy) {
        List<String> fields = Arrays.asList(sortBy.split(","));
        int index = fields.indexOf("status");
        if (index >= 0)
            fields.set(index, "propertyStatus");
        return fields;
    }

    public SimpleAppointmentDTO searchAppointments(String patientName, String sortBy,
                                                   int page, String direction, int size) {
        Patient patient = patientRepository.getBypatientName(patientName);

        SimpleAppointmentDTO simpleAppointmentDTO = new SimpleAppointmentDTO();
        List<String> sortByList = fixSortBy(sortBy);
        List<Sort.Order> sortByOrders = sortByList.stream().map(oe -> new Sort.Order(Sort.Direction.fromString(direction), oe))
                .collect(Collectors.toList());
        Page<Appointment> appointments = appointmentRepository.findByPatientID(patient.getId(),
                PageRequest.of(page,size,Sort.by(sortByOrders)));
        simpleAppointmentDTO.setValues(appointmentMapper.mapToAppointmentDTOList(appointments.getContent()));
        simpleAppointmentDTO.setPageCount(appointments.getTotalPages());
        simpleAppointmentDTO.setCount(appointments.getTotalElements());
        simpleAppointmentDTO.setPage(page);
        simpleAppointmentDTO.setSize(size);
        return simpleAppointmentDTO;
    }
}

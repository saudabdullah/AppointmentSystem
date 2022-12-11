package com.clinic.appointmentSystem.controllers;

import com.clinic.appointmentSystem.dto.AppointmentDTO;
import com.clinic.appointmentSystem.services.AppointmentSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentSerivce appointmentSerivce;

    @PostMapping(value = "/{patientId}/saveAppointment",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public ResponseEntity<AppointmentDTO> saveAppointment(@PathVariable("patientId")UUID patientId,
                                                          @ModelAttribute("form-data") AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentSerivce.saveAppointment(patientId,appointmentDTO));
    }
    @GetMapping("/getTodayAppointments")
    @ResponseBody
    public ResponseEntity<List<AppointmentDTO>> getTodayAppointments(){
        return ResponseEntity.ok(appointmentSerivce.getTodayAppointmets());
    }

    @DeleteMapping("/{appointmentId}/deleteAppointment")
    ResponseEntity<Object> deleteAppointment(@PathVariable("appointmentId")UUID appointmentId,String reason){
        appointmentSerivce.deleteAppointment(appointmentId,reason);
        String message = "appointment deleted successfully";
        return ResponseEntity.ok(message);
    }

}

package com.clinic.appointmentSystem.controllers;

import com.clinic.appointmentSystem.dto.AppointmentDTO;
import com.clinic.appointmentSystem.dto.SimpleAppointmentDTO;
import com.clinic.appointmentSystem.services.AppointmentSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentSerivce appointmentSerivce;

    @PostMapping(value = "/{patientId}/saveAppointment", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public ResponseEntity<AppointmentDTO> saveAppointment(@PathVariable("patientId") UUID patientId,
                                                          @ModelAttribute("form-data") AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentSerivce.saveAppointment(patientId, appointmentDTO));
    }

    @GetMapping("/getTodayAppointments")
    @ResponseBody
    public ResponseEntity<List<AppointmentDTO>> getTodayAppointments() {
        return ResponseEntity.ok(appointmentSerivce.getTodayAppointmets());
    }

    @DeleteMapping("/{appointmentId}/deleteAppointment")
    ResponseEntity<Object> deleteAppointment(@PathVariable("appointmentId") UUID appointmentId, String reason) {
        appointmentSerivce.deleteAppointment(appointmentId, reason);
        String message = "appointment deleted successfully";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{patientId}/getAppointmentHistory")
    @ResponseBody
    public ResponseEntity<List<AppointmentDTO>> getAppointmentHistory(@PathVariable("patientId") UUID patientId) {
        return ResponseEntity.ok(appointmentSerivce.getAppointmentHistory(patientId));
    }

    @GetMapping("/getFilteredAppointments/{patientId}")
    @ResponseBody
    public ResponseEntity<SimpleAppointmentDTO> getFilteredProperties(
            @RequestParam(name = "date", required = false) LocalDate appointmentDate,
            @RequestParam(name = "sortBy", required = false, defaultValue = "appointmentDate") String sortBy,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction,
            @PathVariable(name = "patientId") UUID patientID) {

        return ResponseEntity.ok(appointmentSerivce.filterAppointments(patientID, appointmentDate, sortBy, page, size, direction));

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.controller;

import com.example.DoctorPlaza.Backend.dto.AssignedDoctorsResponse;
import com.example.DoctorPlaza.Backend.dto.PatientDoctorResponse;
import com.example.DoctorPlaza.Backend.dto.RegisterPatientRequest;
import com.example.DoctorPlaza.Backend.dto.ReceptionistDashboardResponse;
import com.example.DoctorPlaza.Backend.dto.ReceptionistQueueManagementResponse;
import com.example.DoctorPlaza.Backend.dto.VisitRequest;
import com.example.DoctorPlaza.Backend.models.Patient;
import com.example.DoctorPlaza.Backend.service.ReceptionistService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
    
    private final ReceptionistService receptionistService;
    
    @Autowired
    public ReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }
    
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getRegisteredPatients(@PathVariable UUID id) {
        try {
            List<Patient> patients = receptionistService.getRegisteredPatientsByReceptionistId(id);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/queued-patients/{id}")
    public ResponseEntity<?> getQueuedPatients(@PathVariable UUID id) {
        try {
            List<PatientDoctorResponse> patients = receptionistService.getPatientsInQueue(id);
            return new ResponseEntity<List<PatientDoctorResponse>>(patients, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/dashboard/{id}")
    public ResponseEntity<?> getReceptionistDashboard(@PathVariable UUID id) {
        try {
            ReceptionistDashboardResponse dashboard = receptionistService.getReceptionistDashboard(id);
            return new ResponseEntity<ReceptionistDashboardResponse>(dashboard, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/queue-management/{id}")
    public ResponseEntity<?> getReceptionistQueueueManagement(@PathVariable UUID id) {
        try {
            ReceptionistQueueManagementResponse dashboard = receptionistService.getQueueManagement(id);
            return new ResponseEntity<ReceptionistQueueManagementResponse>(dashboard, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/patients")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientRequest request) {
        try {
            receptionistService.registerPatient(request);
            return new ResponseEntity(Map.of("message", "Patient Added"), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
       
    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getAssignedDoctors(@PathVariable UUID id) {
        try {
            List<AssignedDoctorsResponse> doctors = receptionistService.getAssignedDoctors(id);
            return new ResponseEntity<List<AssignedDoctorsResponse>>(doctors, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/queue")
    public ResponseEntity<?> addPatientToDoctorQueue(@RequestBody VisitRequest request) {
        try {
            receptionistService.addPatientToDoctorQueue(request);
            return new ResponseEntity(Map.of("message", "Patient Added to queue"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/queue/{id}")
    public ResponseEntity<?> markVisitAsComplete(@PathVariable UUID id) {
        try {
            receptionistService.markPatientVisitAsComplete(id);
            return new ResponseEntity(Map.of("message", "Patient visit marked as complete"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}

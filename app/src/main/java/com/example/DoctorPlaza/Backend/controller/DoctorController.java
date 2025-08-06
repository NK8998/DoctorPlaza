/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.controller;

import com.example.DoctorPlaza.Backend.dto.EditMedicalRecordRequest;
import com.example.DoctorPlaza.Backend.dto.AddMedicalRecordRequest;
import com.example.DoctorPlaza.Backend.dto.GetMedicalRecordResponse;
import com.example.DoctorPlaza.Backend.dto.PatientHistoryResponse;
import com.example.DoctorPlaza.Backend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.MedicalRecord;
import com.example.DoctorPlaza.Backend.models.Visit;
import com.example.DoctorPlaza.Backend.service.DoctorService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    
    @GetMapping("/patients/{doctorId}")
    public ResponseEntity<?> getSeenPatients(@PathVariable UUID doctorId) {
        try {
            List<PatientVisitResponse> list = doctorService.getSeenPatients(doctorId);
            return new ResponseEntity<List<PatientVisitResponse>>(list, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/patients/{patientId}/history")
    public ResponseEntity<?> getPatientMedicalHistory(@PathVariable UUID patientId) {
        try {
            List<PatientHistoryResponse> list = doctorService.getPatientHistory(patientId);
            return new ResponseEntity<List<PatientHistoryResponse>>(list, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/queue/{doctorId}")
    public ResponseEntity<?> getDoctorQueue(@PathVariable UUID doctorId) {
        try {
            List<PatientVisitResponse> queue = doctorService.getDoctorQueue(doctorId);
            return new ResponseEntity<List<PatientVisitResponse>>(queue, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/records/{doctorId}")
    public ResponseEntity<?> getAllMedicalRecord(@PathVariable UUID doctorId) {
        try {
            List<GetMedicalRecordResponse> list = doctorService.getAllMedicalRecords(doctorId);
            return new ResponseEntity<List<GetMedicalRecordResponse>>(list, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/records/add")
    public ResponseEntity<?> insertMedicalRecord(@RequestBody AddMedicalRecordRequest request) {
        try {
            doctorService.addRecord(request);
            return new ResponseEntity<>(Map.of("message", "Record added successfully."), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/records/update/{recordId}")
    public ResponseEntity<?> EditMedicalRecord(@RequestBody EditMedicalRecordRequest request, @PathVariable UUID recordId) {
        try {
            doctorService.editRecord(request);
            return new ResponseEntity<>(Map.of("message", "Record updated successfully."), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.controller;

import com.example.DoctorPlaza.Backend.dto.MedicalHistoryRequest;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.Visit;
import com.example.DoctorPlaza.Backend.service.DoctorService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/doctor")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    
    @GetMapping("/queue/{id}")
    public ResponseEntity<?> getDoctorQueue(@PathVariable UUID id) {
        try {
            List<Visit> queue = doctorService.getDoctorQueue(id);
            return new ResponseEntity<List<Visit>>(queue, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/medical-history")
    public ResponseEntity<?> insertMedicalHistory(@RequestBody MedicalHistoryRequest request) {
        try {
            
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

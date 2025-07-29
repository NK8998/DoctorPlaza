/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.PatientRequest;
import com.example.DoctorPlaza.Backend.dto.VisitRequest;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.Patient;
import com.example.DoctorPlaza.Backend.models.Visit;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HP
 */
public interface ReceptionistService {
    
    void registerPatient(PatientRequest request);
    List<Doctor> getAssignedDoctors(UUID id);
    Visit addPatientToDoctorQueue(VisitRequest request);
    void markPatientVisitAsComplete(UUID id);
    
}

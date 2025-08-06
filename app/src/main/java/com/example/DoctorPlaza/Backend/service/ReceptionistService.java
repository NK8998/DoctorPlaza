/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.AssignedDoctorsResponse;
import com.example.DoctorPlaza.Backend.dto.RegisterPatientRequest;
import com.example.DoctorPlaza.Backend.dto.ReceptionistDashboardResponse;
import com.example.DoctorPlaza.Backend.dto.VisitRequest;
import com.example.DoctorPlaza.Backend.models.Patient;
import com.example.DoctorPlaza.Backend.models.Visit;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HP
 */
public interface ReceptionistService {
    
    void registerPatient(RegisterPatientRequest request);
    List<Patient> getRegisteredPatientsByReceptionistId(UUID id);
    List<AssignedDoctorsResponse> getAssignedDoctors(UUID id);
    Visit addPatientToDoctorQueue(VisitRequest request);
    void markPatientVisitAsComplete(UUID id);
    ReceptionistDashboardResponse getReceptionistDashboard(UUID id);
    
}

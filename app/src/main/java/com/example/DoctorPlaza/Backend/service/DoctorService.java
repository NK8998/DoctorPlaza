/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.EditRecordRequest;
import com.example.DoctorPlaza.Backend.dto.MedicalRecordRequest;
import com.example.DoctorPlaza.Backend.dto.MedicalRecordResponse;
import com.example.DoctorPlaza.Backend.dto.PatientHistoryResponse;
import com.example.DoctorPlaza.Backend.dto.PatientVisitResponse;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HP
 */
public interface DoctorService {
    
    List<PatientVisitResponse> getDoctorQueue(UUID id);
    List<PatientVisitResponse> getSeenPatients(UUID id);
    List<PatientHistoryResponse> getPatientHistory(UUID id);
    void addRecord(MedicalRecordRequest request);
    void editRecord(EditRecordRequest request);
    List<MedicalRecordResponse> getAllMedicalRecords(UUID id);
}

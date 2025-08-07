/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.EditMedicalRecordRequest;
import com.example.DoctorPlaza.Backend.dto.AddMedicalRecordRequest;
import com.example.DoctorPlaza.Backend.dto.GetMedicalRecordResponse;
import com.example.DoctorPlaza.Backend.dto.PatientHistoryResponse;
import com.example.DoctorPlaza.Backend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.MedicalRecord;
import com.example.DoctorPlaza.Backend.models.Patient;
import com.example.DoctorPlaza.Backend.models.Visit;
import com.example.DoctorPlaza.Backend.repository.DoctorRepository;
import com.example.DoctorPlaza.Backend.repository.MedicalRecordRepository;
import com.example.DoctorPlaza.Backend.repository.PatientRepository;
import com.example.DoctorPlaza.Backend.repository.VisitRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    
    @Autowired
    private VisitRepository visitRepository;
    
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientVisitResponse> getDoctorQueue(UUID id) {
        //join tables visit and patient on this doctor's id////
        return visitRepository.findDoctorQueue(id);
    }

    @Override
    public List<PatientVisitResponse> getSeenPatients(UUID id) {
        
        return visitRepository.findSeenPatients(id);
    }

    @Override
    public List<PatientHistoryResponse> getPatientHistory(UUID id) {
        
        return visitRepository.getPatientHistory(id);
    }

    @Override
    @Transactional
    public void addRecord(AddMedicalRecordRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
            .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        Visit visit = visitRepository.findById(request.getVisitId())
            .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
        
        Patient patient = patientRepository.findById(request.getPatientId())
            .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        if (!visit.getPatient().equals(patient)) {
            throw new IllegalArgumentException("Visit does not belong to the specified patient");
        }

        if (!visit.getDoctor().getId().equals(request.getDoctorId())) {
            throw new IllegalArgumentException("Visit was not assigned to the specified doctor");
        }

        MedicalRecord record = new MedicalRecord();
        record.setVisit(visit);
        record.setDoctor(doctor);
        record.setDiagnosis(request.getDiagnosis());
        record.setPrescription(request.getPrescription());
        record.setFollowUp(request.getFollowUp());
        record.setNotes(request.getNotes());

        medicalRecordRepository.save(record);
    }

    @Override
    @Transactional
    public void editRecord(EditMedicalRecordRequest request) {
        MedicalRecord record = medicalRecordRepository
            .findByIdAndDoctorId(request.getRecordId(), request.getDoctorId())
            .orElseThrow(() -> new IllegalArgumentException("Record not found or doctor not authorized"));

        record.setNotes(request.getNotes());
        record.setPrescription(request.getPrescription());
        record.setDiagnosis(request.getDiagnosis());
        record.setFollowUp(request.getFollowUp());

        medicalRecordRepository.save(record);
    }

    @Override
    public List<GetMedicalRecordResponse> getAllMedicalRecords(UUID id) {
        return  medicalRecordRepository.findDetailedRecordsByDoctorId(id);
    }
    
    
    
    
}

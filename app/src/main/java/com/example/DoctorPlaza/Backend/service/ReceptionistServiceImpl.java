/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.PatientRequest;
import com.example.DoctorPlaza.Backend.dto.VisitRequest;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.Patient;
import com.example.DoctorPlaza.Backend.models.Receptionist;
import com.example.DoctorPlaza.Backend.models.Visit;
import com.example.DoctorPlaza.Backend.repository.DoctorRepository;
import com.example.DoctorPlaza.Backend.repository.PatientRepository;
import com.example.DoctorPlaza.Backend.repository.ReceptionistDoctorRepository;
import com.example.DoctorPlaza.Backend.repository.ReceptionistRepository;
import com.example.DoctorPlaza.Backend.repository.VisitRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired
    private ReceptionistRepository receptionistRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private VisitRepository visitRepository;
    
    @Autowired
    private ReceptionistDoctorRepository receptionistDoctorRepository;

    @Override
    public void registerPatient(PatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setBillAddress(request.getBillAddress());
        patient.setPhoneNumber(request.getPhoneNumber());
        
        patientRepository.save(patient);
    }

    @Override
    public List<Doctor> getAssignedDoctors(UUID id) {
        return receptionistDoctorRepository.findDoctorsByReceptionistId(id);
    }

    @Override
    public Visit addPatientToDoctorQueue(VisitRequest request) {
        UUID patientId = request.getPatientId();
        UUID doctorId = request.getDoctorId();
        UUID receptionistId = request.getReceptionistId();

        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Receptionist receptionist = receptionistRepository.findById(receptionistId)
            .orElseThrow(() -> new RuntimeException("Receptionist not found"));

        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setReceptionist(receptionist);
        visit.setSymptoms(request.getSymptoms());

        return visitRepository.save(visit);
    }

    @Override
    @Transactional
    public void markPatientVisitAsComplete(UUID id) {
        visitRepository.markVisitAsComplete(id);
    }
    
}

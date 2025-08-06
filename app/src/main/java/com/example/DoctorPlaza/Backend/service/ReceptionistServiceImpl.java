/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.AssignedDoctorsResponse;
import com.example.DoctorPlaza.Backend.dto.PatientDoctorResponse;
import com.example.DoctorPlaza.Backend.dto.RegisterPatientRequest;
import com.example.DoctorPlaza.Backend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Backend.dto.ReceptionistDashboardResponse;
import com.example.DoctorPlaza.Backend.dto.ReceptionistQueueManagementResponse;
import com.example.DoctorPlaza.Backend.dto.VisitRequest;
import com.example.DoctorPlaza.Backend.dto.VisitStatusTotals;
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
    public void registerPatient(RegisterPatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setBillAddress(request.getBillAddress());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setSymptoms(request.getSymptoms());
        patient.setReceptionistId(request.getReceptionistId());
        
        patientRepository.save(patient);
    }

    @Override
    public List<AssignedDoctorsResponse> getAssignedDoctors(UUID id) {
        return receptionistDoctorRepository.findDoctorsWithPatientCountByReceptionistId(id);
    }

    @Override
    @Transactional
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

        return visitRepository.save(visit);
    }

    @Override
    @Transactional
    public void markPatientVisitAsComplete(UUID id) {
        visitRepository.markVisitAsComplete(id);
    }

    @Override
    public List<Patient> getRegisteredPatientsByReceptionistId(UUID id) {
        return patientRepository.findByReceptionistId(id);
    }
    
    @Override
    @Transactional
    public ReceptionistDashboardResponse getReceptionistDashboard(UUID id){
        VisitStatusTotals visitStatustTotal =  visitRepository.getVisitStatusTotals(id);
        List<AssignedDoctorsResponse> assingedDoctors = receptionistDoctorRepository.findDoctorsWithPatientCountByReceptionistId(id);
        ReceptionistDashboardResponse response = new ReceptionistDashboardResponse();
        response.setVisitsStatusTotals(visitStatustTotal);
        response.setAssignedDoctors(assingedDoctors);
        return response;
    }

    @Override
    public ReceptionistQueueManagementResponse getQueueManagement(UUID id) {
        List<AssignedDoctorsResponse> assignedDoctors = receptionistDoctorRepository.findDoctorsWithPatientCountByReceptionistId(id);
        List<Patient> patientsNotInQueue = patientRepository.findPatientsNotInQueueByReceptionistId(id);
                
        ReceptionistQueueManagementResponse response  = new ReceptionistQueueManagementResponse();
        response.setAssignedDoctors(assignedDoctors);
        response.setPatientsNotInQueue(patientsNotInQueue);
        return response;
    }

    @Override
    public List<PatientDoctorResponse> getPatientsInQueue(UUID id) {
        return visitRepository.getPatientsInQueue(id);
    }
            
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import com.example.DoctorPlaza.Backend.Enums.VisitStatus;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class PatientVisitResponse {
    private UUID visitId;
    private String patientName;
    private int patientAge;
    private String symptoms;
    private VisitStatus status; 

    public PatientVisitResponse(UUID visitId, String patientName, int patientAge, String symptoms, VisitStatus status) {
        this.visitId = visitId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.symptoms = symptoms;
        this.status = status;
    }

    public UUID getVisitId() {
        return visitId;
    }

    public void setVisitId(UUID visitId) {
        this.visitId = visitId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }
    
    
    
    
}

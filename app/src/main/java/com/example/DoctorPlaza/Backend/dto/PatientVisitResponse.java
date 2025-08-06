/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import com.example.DoctorPlaza.Backend.Enums.VisitStatus;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class PatientVisitResponse {
    private UUID visitId;
    private UUID patientId;
    private String patientName;
    private int patientAge;
    private String symptoms;
    private VisitStatus status; 
    private Instant queuedAt;
    private Instant completedAt;


    public PatientVisitResponse(UUID visitId, UUID patientId, String patientName, int patientAge, String symptoms, VisitStatus status, Instant queuedAt, Instant completedAt) {
        this.visitId = visitId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.symptoms = symptoms;
        this.status = status;
        this.queuedAt = queuedAt;
        this.completedAt = completedAt;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
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
    
    public Instant getQueuedAt() {
        return queuedAt;
    }

    public void setQueuedAt(Instant queuedAt) {
        this.queuedAt = queuedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    
    @Override
    public String toString(){
        return "Name: " + this.patientName +
                "Age: " + this.patientAge +
                "Symptoms: " + this.patientAge +
                "status: " + this.status +
                "queued at: " + this.queuedAt +
                "completed at: " + this.completedAt;
     }
    
    
}

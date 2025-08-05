/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.dto;

import com.example.DoctorPlaza.Frontend.Enums.VisitStatus;
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
        return  "Name: " + this.patientName + "\n" +
                "Age: " + this.patientAge + "\n" +
                "Symptoms: " + this.patientAge + "\n" +
                "status: " + this.status + "\n" +
                "queued at: " + this.queuedAt + "\n" +
                "completed at: " + this.completedAt + "\n";
     }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import com.example.DoctorPlaza.Backend.Enums.VisitStatus;
import java.util.UUID;
import java.time.Instant;

/**
 *
 * @author HP
 */
public class PatientHistoryResponse {

    private UUID visitId;
    private String doctorName;
    private String doctorSpecialization;
    private String symptoms;
    private VisitStatus status;
    private Instant queuedAt;
    private Instant completedAt;

    public PatientHistoryResponse(
        UUID visitId,
        String doctorName,
        String doctorSpecialization,
        String symptoms,
        VisitStatus status,
        Instant queuedAt,
        Instant completedAt
    ) {
        this.visitId = visitId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.symptoms = symptoms;
        this.status = status;
        this.queuedAt = queuedAt;
        this.completedAt = completedAt;
    }

    // Getters and Setters (or use Lombok if you want to cut boilerplate)
    public UUID getVisitId() {
        return visitId;
    }

    public void setVisitId(UUID visitId) {
        this.visitId = visitId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
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
}


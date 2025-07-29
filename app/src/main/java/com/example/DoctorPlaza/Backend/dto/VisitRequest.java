/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class VisitRequest {
    @NotBlank private UUID patientId;
    @NotBlank private UUID doctorId;
    @NotBlank private UUID receptionistId;
    @NotBlank private String symptoms;

    public VisitRequest(UUID patientId, UUID doctorId, UUID receptionistId, String symptoms) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.receptionistId = receptionistId;
        this.symptoms = symptoms;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public UUID getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(UUID receptionistId) {
        this.receptionistId = receptionistId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}

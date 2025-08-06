/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.dto;

import java.util.UUID;

/**
 *
 * @author HP
 */
public class VisitRequest {
    private UUID patientId;
    private UUID doctorId;
    private UUID receptionistId;
    
    public VisitRequest(){
        
    }

    public VisitRequest(UUID patientId, UUID doctorId, UUID receptionistId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.receptionistId = receptionistId;
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
}

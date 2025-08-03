/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import com.example.DoctorPlaza.Backend.models.MedicalRecord;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class MedicalRecordResponse {
    private UUID id;
    private UUID visitId;
    private UUID doctorId;
    private String notes;
    private Instant createdAt;

    // constructor
    public MedicalRecordResponse(MedicalRecord record) {
        this.id = record.getId();
        this.visitId = record.getVisit().getId();
        this.doctorId = record.getDoctor().getId();
        this.notes = record.getNotes();
        this.createdAt = record.getCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getVisitId() {
        return visitId;
    }

    public void setVisitId(UUID visitId) {
        this.visitId = visitId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

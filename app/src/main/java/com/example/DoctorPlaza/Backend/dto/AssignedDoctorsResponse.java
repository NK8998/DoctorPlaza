/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import com.example.DoctorPlaza.Backend.Enums.UserRole;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class AssignedDoctorsResponse {
    private UUID id;

    private String name;

    private String email;

    private String passwordHash;

    private UserRole role;

    private Boolean isActive;
    
    private String specialization;

    private String bio;
    
    private Long patientCount;

    public AssignedDoctorsResponse(UUID id, String name, String email, String passwordHash, UserRole role, Boolean isActive, String specialization, String bio, Long patientCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive;
        this.specialization = specialization;
        this.bio = bio;
        this.patientCount = patientCount;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(Long patientCount) {
        this.patientCount = patientCount;
    }
    
}

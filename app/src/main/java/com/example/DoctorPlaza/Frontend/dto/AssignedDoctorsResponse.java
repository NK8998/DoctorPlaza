/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.dto;

import com.example.DoctorPlaza.Backend.Enums.UserRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Boolean isActive = false;
    
    private String specialization;

    private String bio;
    
    private int patientCount;
    
    public AssignedDoctorsResponse(){
        
    }
    
    @JsonCreator
    public AssignedDoctorsResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("passwordHash") String passwordHash,
        @JsonProperty("role") UserRole role,
        @JsonProperty("isActive") Boolean isActive,
        @JsonProperty("specialization") String specialization,
        @JsonProperty("bio") String bio,
        @JsonProperty("visitCount") int visitCount // maps JSON field to patientCount
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive;
        this.specialization = specialization;
        this.bio = bio;
        this.patientCount = visitCount;
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

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }
    
}

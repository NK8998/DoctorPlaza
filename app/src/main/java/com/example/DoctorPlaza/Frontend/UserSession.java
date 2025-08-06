/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend;

import com.example.DoctorPlaza.Frontend.Enums.UserRole;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Admin
 */



public class UserSession {
    private static final UserSession instance = new UserSession();

    private UUID userId;
    private String name;
    private UserRole role;
    private String email;
    private String specialization;

    // Replacing selectedPatientId, selectedVisitId, and patientName
    private final Map<String, Object> selectedPatientInfo = new HashMap<>();

    private UserSession() {}

    public static UserSession getInstance() {
        return instance;
    }

    // ==== Basic User Info ====
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isDoctor() {
        return UserRole.DOCTOR.equals(role);
    }

    // ==== Patient Info as Map ====
    public void setSelectedPatientInfo(String name, UUID patientId, UUID visitId) {
        selectedPatientInfo.put("patientId", patientId);
        selectedPatientInfo.put("name", name);
        selectedPatientInfo.put("visitId", visitId);
    }

    public UUID getSelectedPatientId() {
        return (UUID) selectedPatientInfo.get("patientId");
    }

    public UUID getSelectedVisitId() {
        return (UUID) selectedPatientInfo.get("visitId");
    }

    public String getPatientName() {
        return (String) selectedPatientInfo.get("name");
    }

    public void clearSelectedPatientInfo() {
        selectedPatientInfo.clear();
    }

    public Map<String, Object> getSelectedPatientInfo() {
        return new HashMap<>(selectedPatientInfo); // return a copy to prevent outside mutation
    }

    // ==== Session Cleanup ====
    public void clear() {
        this.userId = null;
        this.name = null;
        this.email = null;
        this.role = null;
        this.specialization = null;
        this.selectedPatientInfo.clear();
    }

    @Override
    public String toString() {
        return "UserId: " + this.userId + "\n" +
               "Name: " + this.name + "\n" +
               "Email: " + this.email + "\n" +
               "Role: " + this.role + "\n";
    }
}



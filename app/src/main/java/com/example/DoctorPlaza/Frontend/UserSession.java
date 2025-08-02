/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend;

import com.example.DoctorPlaza.Frontend.Enums.UserRole;
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

    private UserSession() {}

    public static UserSession getInstance() {
        return instance;
    }

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
    
    public void clear() {
        this.userId = null;
        this.name = null;
        this.email = null;
        this.role = null;
        this.specialization = null;
    }
}


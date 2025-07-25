/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "doctors")
public class Doctor {
    
    @Id
    private UUID id;
    
    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "bio")
    private String bio;

    public Doctor() {}
    
    public Doctor(UUID id, String specialization, String bio, User user) {
        this.id = id;
        this.specialization = specialization;
        this.bio = bio;
    }
    
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    @Override
    public String toString() {
        return super.toString() + ", Doctor [specialization=" + specialization + "]";
    }
}


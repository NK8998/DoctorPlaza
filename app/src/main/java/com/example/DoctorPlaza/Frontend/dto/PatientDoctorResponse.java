/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.dto;

import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class PatientDoctorResponse {
    private UUID id;
    private String name;
    private int age;
    private String phoneNumber;
    private String symptoms;
    private Instant visitCreatedAt;
    private String doctorName;
    private String followUp;
    
    public PatientDoctorResponse() {
        
    }

    public PatientDoctorResponse(UUID id, String name, int age, String phoneNumber, String symptoms, Instant visitCreatedAt, String doctorName, String followUp) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.symptoms = symptoms;
        this.visitCreatedAt = visitCreatedAt;
        this.doctorName = doctorName;
        this.followUp = followUp;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Instant getVisitCreatedAt() {
        return visitCreatedAt;
    }

    public void setVisitCreatedAt(Instant visitCreatedAt) {
        this.visitCreatedAt = visitCreatedAt;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    
}

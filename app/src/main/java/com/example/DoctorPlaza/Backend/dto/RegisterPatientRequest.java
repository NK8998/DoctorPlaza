/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class RegisterPatientRequest {
    @NotBlank private String name;

    @Min(0) private int age;

    @NotBlank private String phoneNumber;

    @NotBlank private String billAddress;
    
    @NotBlank private String symptoms;
    
    @NotBlank private UUID receptionistId;
    
    public RegisterPatientRequest(){
        
    }

    public RegisterPatientRequest(String name, int age, String phoneNumber, String billAddress, String symptoms, UUID receptionistId) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.billAddress = billAddress;
        this.symptoms = symptoms;
        this.receptionistId = receptionistId;
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

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public UUID getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(UUID receptionistId) {
        this.receptionistId = receptionistId;
    }

    
    
    

}

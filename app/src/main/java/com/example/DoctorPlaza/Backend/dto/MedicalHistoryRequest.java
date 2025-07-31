/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class MedicalHistoryRequest {
    @NotBlank UUID patientId;
    
    
}

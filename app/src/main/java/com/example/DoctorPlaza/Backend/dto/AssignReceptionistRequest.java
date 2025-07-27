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
public class AssignReceptionistRequest {
    
    @NotBlank private UUID receptionist_id;
    @NotBlank private UUID doctor_id;

    public AssignReceptionistRequest(UUID receptionist_id, UUID doctor_id) {
        this.receptionist_id = receptionist_id;
        this.doctor_id = doctor_id;
    }

    public UUID getReceptionist_id() {
        return receptionist_id;
    }

    public void setReceptionist_id(UUID receptionist_id) {
        this.receptionist_id = receptionist_id;
    }

    public UUID getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(UUID doctor_id) {
        this.doctor_id = doctor_id;
    }
    
    
    
        
    
}

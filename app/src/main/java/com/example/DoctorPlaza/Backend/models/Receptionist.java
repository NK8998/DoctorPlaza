/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.models;

import com.example.DoctorPlaza.Backend.Enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "receptionists")
public class Receptionist extends User{
    @Id
    private UUID id;
    
    public Receptionist() {}
    
    public Receptionist(UUID id){
        this.id = id;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    @Override
    public String toString() {
        return super.toString() + ", Receptionist";
    }
}


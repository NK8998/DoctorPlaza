/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.repository;

import com.example.DoctorPlaza.Backend.models.Doctor;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HP
 */
public interface DoctorRepository extends JpaRepository<Doctor, UUID>{
    // Get all doctors 
    List<Doctor> findAll();

    // Insert a doctor 
    Doctor save(Doctor doctor);

    // TODO: update doctor fields
    
}

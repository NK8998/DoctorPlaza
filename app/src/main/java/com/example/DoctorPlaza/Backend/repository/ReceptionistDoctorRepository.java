/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.repository;

import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.Receptionist;
import com.example.DoctorPlaza.Backend.models.ReceptionistDoctor;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HP
 */
public interface ReceptionistDoctorRepository extends JpaRepository<ReceptionistDoctor, UUID> {
    
    List<ReceptionistDoctor> findByDoctor(Doctor doctor);
    List<ReceptionistDoctor> findByReceptionist(Receptionist receptionist);
    boolean existsByReceptionistAndDoctor(Receptionist receptionist, Doctor doctor);
    
}

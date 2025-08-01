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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HP
 */
public interface ReceptionistDoctorRepository extends JpaRepository<ReceptionistDoctor, UUID> {
    
    List<ReceptionistDoctor> findByDoctor(Doctor doctor);
    List<ReceptionistDoctor> findByReceptionist(Receptionist receptionist);
    
    @Query("SELECT rd.receptionist FROM ReceptionistDoctor rd WHERE rd.doctor.id = :doctorId")
    List<Receptionist> findReceptionistsByDoctorId(@Param("doctorId") UUID doctorId);
    
    @Query("SELECT rd.doctor FROM ReceptionistDoctor rd WHERE rd.receptionist.id = :receptionistId")
    List<Doctor> findDoctorsByReceptionistId(@Param("receptionistId") UUID receptionistId);
    
    boolean existsByReceptionistAndDoctor(Receptionist receptionist, Doctor doctor);
    
}

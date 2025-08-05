/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.repository;

import com.example.DoctorPlaza.Backend.dto.PatientHistoryResponse;
import com.example.DoctorPlaza.Backend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Backend.models.Visit;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HP
 */
public interface VisitRepository extends JpaRepository<Visit, UUID> {
        
    @Query("SELECT new com.example.DoctorPlaza.Backend.dto.PatientVisitResponse(" +
           "v.id, p.id, p.name, p.age, v.symptoms, v.status, v.queuedAt, v.completedAt) " +
           "FROM Visit v JOIN v.patient p WHERE v.doctor.id = :doctorId")
    List<PatientVisitResponse> findDoctorQueue(@Param("doctorId") UUID doctorId);

    @Query("SELECT new com.example.DoctorPlaza.Backend.dto.PatientVisitResponse(" +
       "v.id, p.id, p.name, p.age, v.symptoms, v.status, v.queuedAt, v.completedAt) " +
       "FROM Visit v JOIN v.patient p " +
       "WHERE v.doctor.id = :doctorId AND v.status = com.example.DoctorPlaza.Backend.Enums.VisitStatus.COMPLETED")
    List<PatientVisitResponse> findSeenPatients(@Param("doctorId") UUID doctorId);
    
    @Query("SELECT new com.example.DoctorPlaza.Backend.dto.PatientHistoryResponse(" +
           "v.id, d.name, d.specialization, v.symptoms, v.status, v.queuedAt, v.completedAt) " +
           "FROM Visit v " +
           "JOIN v.doctor d " +
           "WHERE v.patient.id = :patientId " +
           "ORDER BY v.queuedAt DESC")
    List<PatientHistoryResponse> getPatientHistory(@Param("patientId") UUID patientId);

    @Modifying
    @Query("UPDATE Visit v SET v.status = COMPLETED, v.completedAt = CURRENT_TIMESTAMP WHERE v.id = :id")
    void markVisitAsComplete(@Param("id") UUID id);

}

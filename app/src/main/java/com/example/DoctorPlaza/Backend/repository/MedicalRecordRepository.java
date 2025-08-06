/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.repository;

import com.example.DoctorPlaza.Backend.dto.GetMedicalRecordResponse;
import com.example.DoctorPlaza.Backend.models.MedicalRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {
  
    // Find record only if it belongs to a specific doctor
    Optional<MedicalRecord> findByIdAndDoctorId(UUID id, UUID doctorId);
    
    @Query("SELECT new com.example.DoctorPlaza.Backend.dto.GetMedicalRecordResponse(" +
       "m.id, m.visit.id, m.doctor.id, p.name, m.notes, m.diagnosis, m.prescription, m.followUp, m.createdAt) " +
       "FROM MedicalRecord m " +
       "JOIN m.visit v " +
       "JOIN v.patient p " +
       "WHERE m.doctor.id = :doctorId")
    List<GetMedicalRecordResponse> findDetailedRecordsByDoctorId(@Param("doctorId") UUID doctorId);

    @Modifying
    @Query("UPDATE MedicalRecord m SET m.notes = :notes WHERE m.id = :id AND m.doctor.id = :doctorId")
    int updateNotesIfOwner(UUID id, UUID doctorId, String notes);

}

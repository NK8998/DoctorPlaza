/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.AssignReceptionistRequest;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.Receptionist;
import com.example.DoctorPlaza.Backend.models.ReceptionistDoctor;
import com.example.DoctorPlaza.Backend.models.User;
import com.example.DoctorPlaza.Backend.repository.DoctorRepository;
import com.example.DoctorPlaza.Backend.repository.ReceptionistDoctorRepository;
import com.example.DoctorPlaza.Backend.repository.ReceptionistRepository;
import com.example.DoctorPlaza.Backend.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class AdminServiceImpl implements AdminService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ReceptionistRepository receptionistRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private ReceptionistDoctorRepository receptionistDoctorRepository;

    @Override
    public List<User> getAllPendingUsers() {
        return userRepository.findByIsActiveFalse();
    }

    @Override
    public void approveUser(UUID id, boolean value) {
        userRepository.updateIsActiveById(id, value);
    }

    @Override
    public void rejectUser(UUID id, boolean value) {
        userRepository.updateIsActiveById(id, value);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersExcludingAdmin() {
        return userRepository.findByRoleNot("ADMIN");
    }

    @Override
    public void assignReceptionistToDoctor(AssignReceptionistRequest request) {
    Receptionist receptionist = receptionistRepository.findById(request.getReceptionist_id())
        .orElseThrow(() -> new RuntimeException("Receptionist not found"));

    Doctor doctor = doctorRepository.findById(request.getDoctor_id())
        .orElseThrow(() -> new RuntimeException("Doctor not found"));

    // Check if already assigned
    if (receptionistDoctorRepository.existsByReceptionistAndDoctor(receptionist, doctor)) {
        throw new RuntimeException("Already assigned");
    }

    ReceptionistDoctor rd = new ReceptionistDoctor(receptionist, doctor);
    receptionistDoctorRepository.save(rd);
    }
    
    @Override
    public List<User> getActiveUsers(){
        return userRepository.findByIsActive(true);
    }
    
}

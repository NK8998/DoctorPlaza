/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;


import com.example.DoctorPlaza.Backend.Enums.UserRole;
import com.example.DoctorPlaza.Backend.dto.LoginRequest;
import com.example.DoctorPlaza.Backend.dto.SignupRequest;
import com.example.DoctorPlaza.Backend.dto.UserResponse;
import com.example.DoctorPlaza.Backend.models.Doctor;
import com.example.DoctorPlaza.Backend.models.Receptionist;
import com.example.DoctorPlaza.Backend.models.User;
import com.example.DoctorPlaza.Backend.repository.DoctorRepository;
import com.example.DoctorPlaza.Backend.repository.ReceptionistRepository;
import com.example.DoctorPlaza.Backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private PasswordService passwordService;

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId().toString());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return response;
    }

    @Override
    @Transactional
    public UserResponse signUp(SignupRequest request) {
        
        try{
            
            UserRole role = UserRole.valueOf(request.getRole().toUpperCase());
            if (role == UserRole.ADMIN) {
                throw new RuntimeException("Invalid role provided");
            }
            
            // Check if user already exists
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new RuntimeException("Email already in use");
            }

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordService.encode(request.getPassword()));
            user.setRole(role);
            user.setIsActive(false);

            User savedUser = userRepository.save(user);

            if (savedUser.getRole() == UserRole.DOCTOR) {
                
                if (request.getSpecialization() == null || request.getSpecialization().trim().isEmpty()) {
                    throw new RuntimeException("Please provide specialization for doctor");
                }

                Doctor doctor = new Doctor();
                doctor.setId(savedUser.getId());
                doctor.setSpecialization(request.getSpecialization());
                doctor.setBio(request.getBio());

                doctorRepository.save(doctor);

            } else if (savedUser.getRole() == UserRole.RECEPTIONIST) {
                Receptionist receptionist = new Receptionist();
                receptionist.setId(savedUser.getId());

                receptionistRepository.save(receptionist);
            }

            return mapToResponse(savedUser);
            
        }catch(IllegalArgumentException e){
            throw new RuntimeException("Invalid role provided");
        }

    }


    @Override
    public UserResponse logIn(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordService.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return mapToResponse(user);
    }
}


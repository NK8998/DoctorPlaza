/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.LoginRequest;
import com.example.DoctorPlaza.Backend.dto.SignupRequest;
import com.example.DoctorPlaza.Backend.dto.UserResponse;
import com.example.DoctorPlaza.Backend.models.User;

/**
 *
 * @author HP
 */
public interface AuthService {
    UserResponse signUp(SignupRequest request); 
    UserResponse logIn(LoginRequest request);
}

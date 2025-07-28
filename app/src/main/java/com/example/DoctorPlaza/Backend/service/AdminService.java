/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.dto.AssignReceptionistRequest;
import com.example.DoctorPlaza.Backend.models.User;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HP
 */
public interface AdminService {
    
    List<User> getAllPendingUsers();
    void approveUser(UUID id, boolean value);
    void rejectUser(UUID id, boolean value);
    List<User> getAllUsers();
    List<User> getAllUsersExcludingAdmin();
    void assignReceptionistToDoctor(AssignReceptionistRequest request);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.controller;

import com.example.DoctorPlaza.Backend.dto.AssignReceptionistRequest;
import com.example.DoctorPlaza.Backend.models.User;
import com.example.DoctorPlaza.Backend.service.AdminService;
import com.example.DoctorPlaza.Backend.service.AuthService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private final AdminService adminService;
    
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    
    @GetMapping("/pending-users")
    public ResponseEntity<?> getAllPendingUsers() {
        try {
            List<User> pendingUsers = adminService.getAllPendingUsers();
            return new ResponseEntity<List<User>>(pendingUsers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = adminService.getAllUsers();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/active-users")
    public ResponseEntity<?> getAllActiveUsers() {
        try {
            List<User> users = adminService.getActiveUsers();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/approve-user/{id}")
    public ResponseEntity<?> approveUser(@PathVariable UUID id) {
        try {
            adminService.approveUser(id, true);
            return new ResponseEntity(Map.of("message", "User approved"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/reject-user/{id}")
    public ResponseEntity<?> rejectUser(@PathVariable UUID id) {
        try {
            adminService.rejectUser(id, false);
            return new ResponseEntity(Map.of("message", "User rejected"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/assign-receptionist")
    public ResponseEntity<?> assignReceptionist(@RequestBody AssignReceptionistRequest request) {
        try {
            adminService.assignReceptionistToDoctor(request);
            return new ResponseEntity(Map.of("message", "Receptionist Assigned"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}

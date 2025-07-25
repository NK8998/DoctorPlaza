/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DoctorPlaza.Backend.models.User;

/**
 *
 * @author HP
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query(value ="select id, name, email, role from \"users\"", nativeQuery = true)
    public Optional<List<User>> getAllUsers();

    public Optional<User> findByEmail(String email);
    
    //Insert a regular user
    User save(User user);
}

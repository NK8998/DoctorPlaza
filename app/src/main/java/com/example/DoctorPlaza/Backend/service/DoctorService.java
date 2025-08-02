/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.models.Visit;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HP
 */
public interface DoctorService {
    
    List<Visit> getDoctorQueue(UUID id);
}

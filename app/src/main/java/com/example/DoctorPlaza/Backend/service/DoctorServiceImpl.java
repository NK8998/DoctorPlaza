/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Backend.service;

import com.example.DoctorPlaza.Backend.models.Visit;
import com.example.DoctorPlaza.Backend.repository.VisitRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public List<Visit> getDoctorQueue(UUID id) {
        //join tables visit and patient on this doctor's id////
        return visitRepository.findByDoctorId(id);
    }
    
}

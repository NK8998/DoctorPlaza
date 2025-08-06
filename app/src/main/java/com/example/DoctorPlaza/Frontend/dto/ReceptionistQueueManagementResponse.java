/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.dto;

import java.util.List;

/**
 *
 * @author HP
 */
public class ReceptionistQueueManagementResponse {
    List<AssignedDoctorsResponse> assignedDoctors;
    List<Patient> patientsNotInQueue;

    public ReceptionistQueueManagementResponse(){
        
    }
    
    public ReceptionistQueueManagementResponse(List<AssignedDoctorsResponse> assignedDoctors, List<Patient> patientsNotInQueue) {
        this.assignedDoctors = assignedDoctors;
        this.patientsNotInQueue = patientsNotInQueue;
    }

    public List<AssignedDoctorsResponse> getAssignedDoctors() {
        return assignedDoctors;
    }

    public void setAssignedDoctors(List<AssignedDoctorsResponse> assignedDoctors) {
        this.assignedDoctors = assignedDoctors;
    }

    public List<Patient> getPatientsNotInQueue() {
        return patientsNotInQueue;
    }

    public void setPatientsNotInQueue(List<Patient> patientsNotInQueue) {
        this.patientsNotInQueue = patientsNotInQueue;
    }
    
    
    
}

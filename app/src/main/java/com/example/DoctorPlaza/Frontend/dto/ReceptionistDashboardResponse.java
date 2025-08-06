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
public class ReceptionistDashboardResponse {
    private List<AssignedDoctorsResponse> assignedDoctors;
    private VisitStatusTotals visitsStatusTotals;

    public VisitStatusTotals getVisitsStatusTotals() {
        return visitsStatusTotals;
    }

    public void setVisitsStatusTotals(VisitStatusTotals visitsStatusTotals) {
        this.visitsStatusTotals = visitsStatusTotals;
    }

    public List<AssignedDoctorsResponse> getAssignedDoctors() {
        return assignedDoctors;
    }

    public void setAssignedDoctors(List<AssignedDoctorsResponse> assignedDoctors) {
        this.assignedDoctors = assignedDoctors;
    }


    
}

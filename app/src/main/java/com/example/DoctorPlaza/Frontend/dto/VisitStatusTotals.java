/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.dto;

/**
 *
 * @author HP
 */
public class VisitStatusTotals {
    private Long completed;
    private Long waiting;

    public VisitStatusTotals() {
    // Required for Jackson
    }
    
    public VisitStatusTotals(Long completed, Long waiting) {
        this.completed = completed;
        this.waiting = waiting;
    }

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    public Long getWaiting() {
        return waiting;
    }

    public void setWaiting(Long waiting) {
        this.waiting = waiting;
    }
    
}

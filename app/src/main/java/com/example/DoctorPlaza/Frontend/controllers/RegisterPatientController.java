/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegisterPatientController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    }

    @FXML
    private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/receptionistDashboard.fxml", new ReceptionistDashboardController());
    }

    @FXML
    private void handleRegister(ActionEvent event) {
         System.out.println("Already on Register Patient page");
    }

    @FXML
    private void handleQueue(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/receptionist/queueManagement.fxml", new QueueManagementController());
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
       SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/receptionist/queueManagement.fxml", new DischargeController()); 
    }  
    
}

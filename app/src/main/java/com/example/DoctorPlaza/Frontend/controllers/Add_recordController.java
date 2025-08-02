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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Add_recordController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnPatientQueue;
    @FXML
    private Button btnPatientHistory;
    @FXML
    private Button btnAddRecord;
    @FXML
    private Button btnEditRecord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML

       private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DashboardController());
    }

    @FXML
    private void handlePatientQueue(ActionEvent event) {
        
       SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_queue.fxml", new Patient_queueController());
    }

    @FXML
    private void handlePatientHistory(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_history.fxml", new Patient_historyController());
    }

    @FXML
    private void handleAddRecord(ActionEvent event) {
        System.out.println("Already on Add Record page. ");
    }

    @FXML
    private void handleEditRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
    }
}

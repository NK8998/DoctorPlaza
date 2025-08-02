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
public class Patient_historyController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnPatientQueue;
    @FXML
    private Button btnAddRecord;
    @FXML
    private Button btnEditRecords;

    /**
     * Initializes the controller class.
     */
   @FXML

<<<<<<< HEAD
       private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DashboardController());
=======
    @FXML
    private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DoctorDashboardController());
>>>>>>> 9eb2ac67ac296486addade784f32e4bb6f017c85
    }

    @FXML
    private void handlePatientQueue(ActionEvent event) {
<<<<<<< HEAD
        
       SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_queue.fxml", new Patient_queueController());
    }

    @FXML
    private void handlePatientHistory(ActionEvent event) {
        System.out.println("Already on Patient History page. ");
=======
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_queue.fxml", new Patient_queueController());
>>>>>>> 9eb2ac67ac296486addade784f32e4bb6f017c85
    }

    @FXML
    private void handleAddRecord(ActionEvent event) {
<<<<<<< HEAD
        
=======
>>>>>>> 9eb2ac67ac296486addade784f32e4bb6f017c85
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/add_record.fxml", new Add_recordController());
    }

    @FXML
<<<<<<< HEAD
    private void handleEditRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
    private void handleEditRecords(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
>>>>>>> 9eb2ac67ac296486addade784f32e4bb6f017c85
    }
}

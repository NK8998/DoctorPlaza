package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DischargeController implements Initializable {

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
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/receptionist/registerPatient.fxml", new RegisterPatientController());
    }

    @FXML
    private void handleQueue(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/receptionist/queueManagement.fxml", new QueueManagementController());
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
        // Already on discharge page
        System.out.println("Already on Discharge page");
    }


}

package com.example.DoctorPlaza.Frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReceptionistDashboardController implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label assignedDoctorLabel;

    @FXML
    private Label doctorNameLabel;

    @FXML
    private Label specializationLabel;

    @FXML
    private Label queueSizeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Replace with dynamic session data later
        String receptionistName = "Rachel";
        String doctorName = "Dr. A. Muli";
        String specialization = "ENT";
        int queueSize = 5;

        welcomeLabel.setText("Welcome, " + receptionistName);
        assignedDoctorLabel.setText("Managing Doctor: " + doctorName);
        doctorNameLabel.setText(doctorName);
        specializationLabel.setText(specialization);
        queueSizeLabel.setText(String.valueOf(queueSize));
    }
}

    /*@FXML
    private void goToRegister(javafx.event.ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/register_patient.fxml", new RegisterPatientController());
    }

    @FXML
    private void goToQueue(javafx.event.ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/queue_management.fxml", new QueueManagementController());
    }

    @FXML
    private void goToDischarge(javafx.event.ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/discharge.fxml", new DischargeController());
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/SignIn.fxml", new SignInController());
    }
}
*/
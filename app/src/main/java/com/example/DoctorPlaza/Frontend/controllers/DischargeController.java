package com.example.DoctorPlaza.Frontend.controllers;

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
        switchScene(event, "/com/example/DoctorPlaza/Frontend/receptionistDashboard.fxml");
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        switchScene(event, "/com/example/DoctorPlaza/Frontend/receptionist/registerPatient.fxml");
    }

    @FXML
    private void handleQueue(ActionEvent event) {
        switchScene(event, "/com/example/DoctorPlaza/Frontend/receptionist/queueManagement.fxml");
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
        // Already on discharge page
        System.out.println("Already on Discharge page");
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

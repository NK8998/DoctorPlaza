/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.dto.ReceptionistDashboardResponse;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.AssignedDoctorsResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReceptionistDashboardController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnQueue;
    @FXML
    private Button btnDischarge;
    @FXML
    private Label lblCompleted;
    @FXML
    private Label lblWaiting;
    @FXML
    private FlowPane assignedDcotorsContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetchDashboardContent();
    }    
    
    private void fetchDashboardContent() {
        UserSession session = UserSession.getInstance();
        String path = "/receptionist/dashboard/" + session.getUserId();

        HttpTask<Void, ReceptionistDashboardResponse> task = new HttpTask<>(
            path,
            null,
            "GET",
            new TypeReference<ReceptionistDashboardResponse>() {}
        );

        task.setOnSucceeded(e -> {
            ReceptionistDashboardResponse response = task.getValue();
            Platform.runLater(() -> updateLabelsAndInsertChildren(response));
        });

        task.setOnFailed(e -> {
            System.err.println("Failed to fetch dashboard: " + task.getException());
        });

        new Thread(task).start();
    }
    
    private void updateLabelsAndInsertChildren(ReceptionistDashboardResponse response) {
        // Update the labels
        lblWaiting.setText(String.valueOf(
            response.getVisitsStatusTotals() != null && response.getVisitsStatusTotals().getWaiting() != null 
                ? response.getVisitsStatusTotals().getWaiting() 
                : 0
        ));

        lblCompleted.setText(String.valueOf(
            response.getVisitsStatusTotals() != null && response.getVisitsStatusTotals().getCompleted() != null 
                ? response.getVisitsStatusTotals().getCompleted() 
                : 0
        ));

        // Clear the container first
        assignedDcotorsContainer.getChildren().clear();

        for (AssignedDoctorsResponse doctor : response.getAssignedDoctors()) {
            VBox card = new VBox(5);
            card.setStyle("-fx-background-color: #fff; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: lightgray; -fx-border-width: 1;");

            Label nameLabel = new Label("Dr. " + doctor.getName());
            nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label specLabel = new Label(doctor.getSpecialization() != null ? doctor.getSpecialization() : "N/A");
            specLabel.setStyle("-fx-text-fill: gray;");

            Label countLabel = new Label(doctor.getPatientCount() + " patients");
            countLabel.setStyle("-fx-background-color: #ffebee; -fx-text-fill: #d32f2f; -fx-padding: 4 8 4 8; -fx-background-radius: 10;");

            card.getChildren().addAll(nameLabel, specLabel, countLabel);

            assignedDcotorsContainer.getChildren().add(card);
        }
    }

    @FXML
    private void handleDashboard(ActionEvent event) {
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/registerPatient.fxml", new RegisterPatientController());
    }

    @FXML
    private void handleQueue(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/queueManagement.fxml", new QueueManagementController());
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/Discharge.fxml", new DischargeController());
    }
    
}

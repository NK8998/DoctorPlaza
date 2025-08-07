/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.AssignedDoctorsResponse;
import com.example.DoctorPlaza.Frontend.dto.Patient;
import com.example.DoctorPlaza.Frontend.dto.ReceptionistQueueManagementResponse;
import com.example.DoctorPlaza.Frontend.dto.StringMessageResponse;
import com.example.DoctorPlaza.Frontend.dto.VisitRequest;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.example.DoctorPlaza.Frontend.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class QueueManagementController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnQueue;
    @FXML
    private Button btnDischarge;
    @FXML
    private Label lblTotalPatients;
    @FXML
    private ComboBox<AssignedDoctorsResponse> assignedDoctorsComboBox;
    @FXML
    private VBox patientsNotInQueueContainer;
    
    private UUID selectedDoctorId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetchQueueManagementData();
    }    

    private void fetchQueueManagementData(){
        UserSession session = UserSession.getInstance();
        String path = "/receptionist/queue-management/" + session.getUserId();

        HttpTask<Void, ReceptionistQueueManagementResponse> task = new HttpTask<>(
            path,
            null,
            "GET",
            new TypeReference<ReceptionistQueueManagementResponse>() {}
        );

        task.setOnSucceeded(e -> {
            ReceptionistQueueManagementResponse response = task.getValue();
            Platform.runLater(() -> updateLabelsAndInsertChildren(response));
        });

        task.setOnFailed(e -> {
            System.err.println("Failed to fetch dashboard: " + task.getException());
        });

        new Thread(task).start();
    }
    
    private void renderComboBox(List<AssignedDoctorsResponse> response){
        assignedDoctorsComboBox.getItems().clear();
        if (response != null) {
            for (AssignedDoctorsResponse doctor : response) {
                assignedDoctorsComboBox.getItems().add(doctor);
            }
        }
        
        assignedDoctorsComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal instanceof AssignedDoctorsResponse doctor) {
                selectedDoctorId = doctor.getId();
                System.out.println("Selected doctor ID: " + selectedDoctorId);
            }
        });


        assignedDoctorsComboBox.setCellFactory(listView -> new ListCell<AssignedDoctorsResponse>() {
            @Override
            protected void updateItem(AssignedDoctorsResponse doctor, boolean empty) {
                super.updateItem(doctor, empty);
                if (doctor != null && !empty) {
                    setText(doctor.getName());
                } else {
                    setText(null);
                }
            }
        });

        assignedDoctorsComboBox.setButtonCell(new ListCell<AssignedDoctorsResponse>() {
            @Override
            protected void updateItem(AssignedDoctorsResponse doctor, boolean empty) {
                super.updateItem(doctor, empty);
                if (doctor != null && !empty) {
                    setText(doctor.getName());
                } else {
                    setText(null);
                }
            }
        });

    }
    
    private void updateLabelsAndInsertChildren(ReceptionistQueueManagementResponse response) {
        // 1. Update label
        lblTotalPatients.setText(String.valueOf(
            response.getPatientsNotInQueue() != null ? response.getPatientsNotInQueue().size() : 0
        ));

        // 2. Populate assignedDoctorsComboBox
        renderComboBox(response.getAssignedDoctors());

        // 3. Clear container
        patientsNotInQueueContainer.getChildren().clear();

        // 4. Add patient cards
        if (response.getPatientsNotInQueue() != null) {
            int count = 1;
            for (Patient patient : response.getPatientsNotInQueue()) {
                final int index = count++;

                // HBox root
                HBox card = new HBox(20);
                card.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-width: 1;");
                card.setAlignment(Pos.CENTER_LEFT);

                // Left VBox with patient number
                VBox leftBox = new VBox(5);
                Label numberLabel = new Label("#" + index);
                numberLabel.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 16px; -fx-font-weight: bold;");
                leftBox.getChildren().add(numberLabel);

                // Middle VBox with patient details
                VBox middleBox = new VBox(5);
                Label nameLabel = new Label(patient.getName());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label symptomLabel = new Label(patient.getSymptoms() != null ? patient.getSymptoms() : "No symptoms");
                symptomLabel.setStyle("-fx-text-fill: #666;");
                Label waitLabel = new Label("⏱ Waiting: 0 min");
                waitLabel.setStyle("-fx-text-fill: #666;");
                middleBox.getChildren().addAll(nameLabel, symptomLabel, waitLabel);
                HBox.setHgrow(middleBox, Priority.ALWAYS);

                // Spacer
                Pane spacer = new Pane();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                // Right HBox with Add button
                HBox rightBox = new HBox(10);
                rightBox.setAlignment(Pos.CENTER);
                Button addButton = new Button("Add");
                addButton.setPrefSize(45, 25);
                addButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-background-radius: 4; -fx-padding: 6;");

                // Button click handler
                addButton.setOnAction(event -> handleAddToQueue(patient));

                rightBox.getChildren().add(addButton);

                // Add everything to card
                card.getChildren().addAll(leftBox, middleBox, spacer, rightBox);

                // Add card to VBox
                patientsNotInQueueContainer.getChildren().add(card);
            }
        }
    }
    
    private void handleAddToQueue(Patient patient) {
        System.out.println("Add button clicked for: " + patient.getName());
        UserSession session = UserSession.getInstance();
        String path = "/receptionist/queue";
        VisitRequest request = new VisitRequest();
        request.setDoctorId(selectedDoctorId);
        request.setPatientId(patient.getId());
        request.setReceptionistId(session.getUserId());
        

        HttpTask<VisitRequest, StringMessageResponse> task = new HttpTask<>(
            path,
            request,
            "POST",
            new TypeReference<StringMessageResponse>() {}
        );

        task.setOnSucceeded(e -> {
            Utils.showInfo("Patient Added to queue");
            fetchQueueManagementData();
        });

        task.setOnFailed(e -> {
            Utils.showError("Failed to add patient to queue");
            System.err.println("Failed to fetch dashboard: " + task.getException());
        });

        new Thread(task).start();
    }


    
    @FXML
    private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/receptionistDashboard.fxml", new ReceptionistDashboardController());
    }
    

    @FXML
    private void handleRegister(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/registerPatient.fxml", new RegisterPatientController());
    }

    @FXML
    private void handleQueue(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/queueManagement.fxml", new QueueManagementController());
        System.out.println("Already on Queue Management page");
 
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
     SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/Discharge.fxml", new DischargeController());
    } 
    
}

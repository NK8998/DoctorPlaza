/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.Enums.VisitStatus;
import com.example.DoctorPlaza.Frontend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.formatDuration;
import static com.example.DoctorPlaza.Frontend.utils.Utils.formatTime;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Patient_queueController implements Initializable {

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
    @FXML
    private VBox patientQueueContainer;
    
    private List<PatientVisitResponse> queued = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDoctorQueue();
    }    
    
    private void getDoctorQueue() {
        UserSession session = UserSession.getInstance();
        String url = "/doctor/queue/" + session.getUserId();

        HttpTask<Void, List<PatientVisitResponse>> task = new HttpTask<>(
            url,
            null,
            "GET",
            new TypeReference<>() {}
        );

        task.setOnSucceeded(e -> {
            processVisits(task.getValue());
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }
    
    private void processVisits(List<PatientVisitResponse> visits){
        for(PatientVisitResponse visit : visits){
            if(visit.getStatus().equals(VisitStatus.WAITING)){
                queued.add(visit);
            }
        }
        insertElements();
    }
    
    private void insertElements() {
        patientQueueContainer.getChildren().clear();

        for (PatientVisitResponse visit : queued) {
            // Name & Reason VBox
            Label nameLabel = new Label(visit.getPatientName());
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

            Label reasonLabel = new Label("Reason: " + visit.getSymptoms());
            reasonLabel.setStyle("-fx-text-fill: #555;");

            // Time Info
            String appointmentTime = formatTime(visit.getQueuedAt());
            String arrivalTime = formatTime(visit.getQueuedAt()); // Assuming same for now
            String wait = formatDuration(Duration.between(visit.getQueuedAt(), Instant.now()));

            Label appointmentLabel = new Label("Appointment: " + appointmentTime);
            appointmentLabel.setStyle("-fx-text-fill: #777;");

            Label arrivalLabel = new Label("Arrival: " + arrivalTime);
            arrivalLabel.setStyle("-fx-text-fill: #777;");

            Label waitLabel = new Label("⏱ Wait: " + wait);
            waitLabel.setStyle("-fx-text-fill: #777;");

            HBox timeBox = new HBox(15, appointmentLabel, arrivalLabel, waitLabel);

            VBox nameReasonBox = new VBox(4, nameLabel, reasonLabel, timeBox);

            // Spacer
            Pane spacer = new Pane();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            // Status Labels
            Label statusLabel = new Label(visit.getStatus().toString().toLowerCase());
            statusLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 3 8; -fx-background-radius: 10;");

            Label waitingLabel = new Label("waiting");
            waitingLabel.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-padding: 3 8; -fx-background-radius: 10;");

            HBox statusBox = new HBox(8, statusLabel, waitingLabel);

            // Buttons
            Button callBtn = new Button("Call Patient");

            Button startVisitBtn = new Button("Start Visit");
           
            startVisitBtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
            startVisitBtn.setOnAction(event -> {
                 UserSession session = UserSession.getInstance();
                 session.setSelectedPatientInfo(
                         visit.getPatientName(),
                         visit.getPatientId(),
                         visit.getVisitId()
                 );
                //store the clicked patient id.
                //navigate
                SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/add_record.fxml", new Add_recordController()); 
            });
            

            HBox buttonBox = new HBox(10, callBtn, startVisitBtn);

            VBox rightBox = new VBox(5, statusBox, buttonBox);
            rightBox.setAlignment(Pos.CENTER_RIGHT);

            // Main HBox
            HBox row = new HBox(15, nameReasonBox, spacer, rightBox);
            row.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: #ddd;");
            row.setAlignment(Pos.CENTER_LEFT);

            // Add to container
            patientQueueContainer.getChildren().add(row);
        }
    }


    @FXML
     private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DoctorDashboardController());
    }

    @FXML
    private void handlePatientQueue(ActionEvent event) {
        
        System.out.println("Already on Patient Queue page. ");
    }
    
    @FXML
    private void handleAddRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/add_record.fxml", new Add_recordController());
    }

    private void handleEditRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
    }

    @FXML
    private void handleEditRecords(ActionEvent event) {
    }

    @FXML
    private void handlePatientHistory(ActionEvent event) {
    }
    
    
}

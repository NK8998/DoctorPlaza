/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Backend.Enums.VisitStatus;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Frontend.service.HttpService;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.formatDuration;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
public class DoctorDashboardController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnPatientQueue;
    @FXML
    private Button btnEditRecords;
    @FXML
    private Label lblName;
    @FXML
    private Label lblWelcome;
    @FXML
    private VBox currentQueue;
    @FXML
    private VBox completedVisits;

    private List<PatientVisitResponse> queued = new ArrayList();
    private List<PatientVisitResponse> completed =  new ArrayList();
    @FXML
    private Label totalPatients;
    @FXML
    private Label totalWaiting;
    @FXML
    private Label totalCompleted;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession session = UserSession.getInstance();
        System.out.println(session);
        lblName.setText("Dr. " + session.getName() );
        lblWelcome.setText("Welcome Dr. " + session.getName() + ". Here is your overview for today.");
        
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
            List<PatientVisitResponse> list = task.getValue();
            processVisits(task.getValue());
            list.forEach(System.out::println);
            insertElements();
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }
    
    private void processVisits(List<PatientVisitResponse> visits){
        for(PatientVisitResponse visit : visits){
            if(visit.getStatus().equals(VisitStatus.COMPLETED)){
                completed.add(visit);
            }else{
                queued.add(visit);
            }
        }
        totalPatients.setText(String.valueOf(visits.size()));
        totalWaiting.setText(String.valueOf(queued.size()));
        totalCompleted.setText(String.valueOf(completed.size()));

    }
    
    private void insertElements() {
        currentQueue.getChildren().clear();
        completedVisits.getChildren().clear();

        for (PatientVisitResponse visit : queued) {
            currentQueue.getChildren().add(createQueueItem(visit));
        }

        for (PatientVisitResponse visit : completed) {
            completedVisits.getChildren().add(createCompletedItem(visit));
        }
    }
    
    
    private HBox createQueueItem(PatientVisitResponse visit) {
        Label nameLabel = new Label(visit.getPatientName());
        nameLabel.setStyle("-fx-font-weight: bold;");

        // Format the time however you like
        String appointmentTime = DateTimeFormatter.ofPattern("h:mm a")
            .withZone(ZoneId.systemDefault())
            .format(visit.getQueuedAt());
        Label timeLabel = new Label("Appointment: " + appointmentTime);
        timeLabel.setStyle("-fx-text-fill: #777;");

        VBox nameTimeBox = new VBox(2, nameLabel, timeLabel);

        Label statusLabel = new Label(visit.getStatus().toString().toLowerCase());
        statusLabel.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-padding: 3 8; -fx-background-radius: 10;");

        Duration waitDuration = Duration.between(visit.getQueuedAt(), Instant.now());
        Label waitTimeLabel = new Label(formatDuration(waitDuration));
        waitTimeLabel.setStyle("-fx-text-fill: #555;");

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox row = new HBox(10, nameTimeBox, spacer, statusLabel, waitTimeLabel);
        row.setStyle("-fx-background-color: #fff5f5; -fx-padding: 10; -fx-background-radius: 6;");
        row.setAlignment(Pos.CENTER_LEFT);

        return row;
    }

    private HBox createCompletedItem(PatientVisitResponse visit) {
        Label nameLabel = new Label(visit.getPatientName());
        nameLabel.setStyle("-fx-font-weight: bold;");

        Label reasonLabel = new Label(visit.getSymptoms());
        reasonLabel.setStyle("-fx-text-fill: #777;");

        VBox nameSymptomBox = new VBox(2, nameLabel, reasonLabel);

        String completedTime = DateTimeFormatter.ofPattern("h:mm a")
            .withZone(ZoneId.systemDefault())
            .format(visit.getCompletedAt());
        Label timeLabel = new Label(completedTime);
        timeLabel.setStyle("-fx-text-fill: #666;");

        Label statusLabel = new Label("completed");
        statusLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 3 8; -fx-background-radius: 10;");

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox row = new HBox(10, nameSymptomBox, spacer, timeLabel, statusLabel);
        row.setStyle("-fx-background-color: #fff5f5; -fx-padding: 10; -fx-background-radius: 6;");
        row.setAlignment(Pos.CENTER_LEFT);

        return row;
    }


    

    @FXML
    private void handleDashboard(ActionEvent event) {
        System.out.println("Already on Doctor Dashboard page. ");
    }

    @FXML
    private void handlePatientQueue(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_queue.fxml", new Patient_queueController());
    }

    private void handleAddRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/add_record.fxml", new Add_recordController());
    }

    @FXML
    private void handleEditRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
    }
    
}

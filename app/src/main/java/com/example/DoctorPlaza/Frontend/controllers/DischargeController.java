package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.AllUsersResponse;
import com.example.DoctorPlaza.Frontend.dto.PatientDoctorResponse;
import com.example.DoctorPlaza.Frontend.dto.StringMessageResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.formatTime;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showError;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DischargeController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnQueue;
    @FXML
    private Button btnDischarge;
    @FXML
    private TextField txtFilter;
    @FXML
    private VBox dischargeContainer;
    
    List<PatientDoctorResponse> patients = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
        fetchPatientsToDischarge();
        
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            List<PatientDoctorResponse> filtered = patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(newValue.toLowerCase()))
                .collect(Collectors.toList());

            dischargeContainer.getChildren().clear();
            for (PatientDoctorResponse patient : filtered) {
                addPatientCard(patient);  
            }
        });
    }
    
    private void fetchPatientsToDischarge(){
        UserSession session = UserSession.getInstance();
        String path = "/receptionist/queued-patients/" + session.getUserId();
        HttpTask<Void, List<PatientDoctorResponse>> task = new HttpTask<>(
            path,
            null,
            "GET",
            new TypeReference<List<PatientDoctorResponse>>() {}
        );

        task.setOnSucceeded(e -> {
            patients = task.getValue();
            createDischargeChildren();
        });

        task.setOnFailed(e -> {
            showError("Failed to fetch users");
            System.out.println("Failed to fetch users: " + task.getException());
        });

        new Thread(task).start();
    }
    
    private void createDischargeChildren() {
        dischargeContainer.getChildren().clear();
        for (PatientDoctorResponse patient : patients) {
            addPatientCard(patient);
        }
    }

    
    private void addPatientCard(PatientDoctorResponse patient) {
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: #e0e0e0;");

        VBox infoBox = new VBox(5);
        HBox.setHgrow(infoBox, Priority.ALWAYS);

        Label nameLabel = new Label(patient.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label visitLabel = new Label("👥 with Dr. " + patient.getDoctorName() + " at " +
                formatTime(patient.getVisitCreatedAt()));
        visitLabel.setStyle("-fx-text-fill: #555;");

        Label symptomsLabel = new Label(patient.getSymptoms());
        symptomsLabel.setStyle("-fx-text-fill: #777;");

        infoBox.getChildren().addAll(nameLabel, visitLabel, symptomsLabel);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button dischargeBtn = new Button("👥 Discharge");
        dischargeBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 10;");
        dischargeBtn.setOnAction(e -> dischargePatient(patient));

        HBox buttonBox = new HBox(dischargeBtn);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(infoBox, spacer, buttonBox);
        dischargeContainer.getChildren().add(hbox);
    }

    
    private void dischargePatient(PatientDoctorResponse patient){
        String path = "/receptionist/queue/" + patient.getId();
        HttpTask<Void, StringMessageResponse> task = new HttpTask<>(
            path,
            null,
            "PATCH",
            new TypeReference<StringMessageResponse>() {}
        );

        task.setOnSucceeded(e -> {
            showInfo("Patient discharged successefully");
            System.out.println("Patient discharged successefully" + task.getValue().getMessage());
            fetchPatientsToDischarge();
        });

        task.setOnFailed(e -> {
            showError("Failed to dischaged patient");
            System.out.println("Failed to dischaged patient: " + task.getException());
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
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
        // Already on discharge page
        System.out.println("Already on Discharge page");
    }


}

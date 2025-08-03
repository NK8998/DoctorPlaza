/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Frontend.service.HttpService;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private Button btnPatientHistory;
    @FXML
    private Button btnAddRecord;
    @FXML
    private Button btnEditRecords;
    @FXML
    private Label lblName;
    @FXML
    private Label lblWelcome;

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
        String url = "http://localhost:8080/doctor/queue/" + session.getUserId();

        HttpTask<Void, List<PatientVisitResponse>> task = new HttpTask<>(
            url,
            null,
            "GET",
            new TypeReference<>() {}
        );

        task.setOnSucceeded(e -> {
            List<PatientVisitResponse> visits = task.getValue();
            System.out.println("Got " + visits.size() + " visits");
            visits.forEach(System.out::println);

        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

    

    @FXML
    private void handleDashboard(ActionEvent event) {
        System.out.println("Already on Doctor Dashboard page. ");
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
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/add_record.fxml", new Add_recordController());
    }

    @FXML
    private void handleEditRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
    }
    
}

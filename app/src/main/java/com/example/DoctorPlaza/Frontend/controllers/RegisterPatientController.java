/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.ReceptionistDashboardResponse;
import com.example.DoctorPlaza.Frontend.dto.RegisterPatientRequest;
import com.example.DoctorPlaza.Frontend.dto.StringMessageResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showError;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegisterPatientController implements Initializable {

    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextArea txtBilling;
    @FXML
    private TextArea txtSymptoms;
    @FXML
    private Button btnRegisterPatient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void registerPatient() {
        UserSession session = UserSession.getInstance();

        String name = txtFullName.getText().trim();
        String ageText = txtAge.getText().trim();
        String phone = txtPhone.getText().trim();
        String billing = txtBilling.getText().trim();
        String symptoms = txtSymptoms.getText().trim();

        // === Validation ===
        if (name.isEmpty() || ageText.isEmpty() || phone.isEmpty() || billing.isEmpty() || symptoms.isEmpty()) {
            showError("Please fill in all the fields.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0 || age > 120) {
                showError("Please enter a valid age.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Age must be a valid number.");
            return;
        }

        if (!phone.matches("\\d{10,15}")) {
            showError("Phone number must be 10 to 15 digits.");
            return;
        }

        // === Build Request ===
        RegisterPatientRequest request = new RegisterPatientRequest();
        request.setName(name);
        request.setAge(age);
        request.setPhoneNumber(phone);
        request.setBillAddress(billing);
        request.setSymptoms(symptoms);
        request.setReceptionistId(session.getUserId());

        String path = "/receptionist/patients";

        HttpTask<RegisterPatientRequest, StringMessageResponse> task = new HttpTask<>(
            path,
            request,
            "POST",
            new TypeReference<StringMessageResponse>() {}
        );

        task.setOnSucceeded(e -> {
            showInfo("Patient registered successfully!");
            clearForm();
        });

        task.setOnFailed(e -> {
            showError("Failed to register patient.");
            System.err.println("Failed to register patient: " + task.getException());
        });

        new Thread(task).start();
    }

    private void clearForm() {
        txtFullName.clear();
        txtAge.clear();
        txtPhone.clear();
        txtBilling.clear();
        txtSymptoms.clear();
    }

    @FXML
    private void onRegisterPatient(ActionEvent event) {
        registerPatient();
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
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/Discharge.fxml", new DischargeController());
    }
    
}

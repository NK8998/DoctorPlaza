/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.AddMedicalRecordRequest;
import com.example.DoctorPlaza.Frontend.dto.StringMessageResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showError;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Add_recordController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnPatientQueue;
    @FXML
    private Button btnEditRecord;
    @FXML
    private ComboBox<String> diagnosisComoBox;
    @FXML
    private Label lblName;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtSymptoms;
    @FXML
    private TextArea txtNotes;
    @FXML
    private TextArea txtInstructions;
    @FXML
    private TextField txtPrescriptions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSession session = UserSession.getInstance();
        lblName.setText(session.getPatientName());
        
        List<String> diagnoses = List.of(
            "Hypertension",
            "Type 2 Diabetes Mellitus",
            "Upper Respiratory Tract Infection",
            "Asthma",
            "Anemia",
            "Migraine",
            "Gastroenteritis",
            "Urinary Tract Infection",
            "Depression",
            "Anxiety Disorder",
            "Acute Bronchitis",
            "Pneumonia",
            "Back Pain",
            "Osteoarthritis",
            "Sinusitis",
            "Conjunctivitis",
            "Tonsillitis",
            "Allergic Rhinitis",
            "Otitis Media",
            "Dermatitis"
        );

        diagnosisComoBox.setItems(FXCollections.observableArrayList(diagnoses));
    }
    
    private void addRecord() {
        UserSession session = UserSession.getInstance();

        // Fetch form values
        String diagnosis = diagnosisComoBox.getValue();
        String prescriptions = txtPrescriptions.getText();
        String notes = txtNotes.getText();
        String followUp = txtInstructions.getText();

        // Validate required session data
        if (session.getSelectedVisitId() == null || session.getSelectedPatientId() == null || session.getUserId() == null) {
            showError("Session error: Visit, patient, or doctor information is missing.");
            return;
        }

        // Validate form input
        if (diagnosis == null || diagnosis.isBlank()) {
            showError("Please select a diagnosis.");
            return;
        }

        if (prescriptions == null || prescriptions.isBlank()) {
            showError("Please enter prescription details.");
            return;
        }

        if (notes == null || notes.isBlank()) {
            showError("Please enter notes.");
            return;
        }

        if (followUp == null || followUp.isBlank()) {
            showError("Please enter follow-up instructions.");
            return;
        }

        // Prepare request payload
        AddMedicalRecordRequest request = new AddMedicalRecordRequest();
        request.setVisitId(session.getSelectedVisitId());
        request.setPatientId(session.getSelectedPatientId());
        request.setDoctorId(session.getUserId());
        request.setDiagnosis(diagnosis);
        request.setPrescription(prescriptions);
        request.setNotes(notes);
        request.setFollowUp(followUp);

        // Send request
        String url = "/doctor/records/add";

        HttpTask<AddMedicalRecordRequest, StringMessageResponse> task = new HttpTask<>(
            url,
            request,
            "POST",
            new TypeReference<>() {}
        );

        task.setOnSucceeded(e -> {
            // You can add success handling here
            showInfo("Medical record added successfully.");
            clearForm();
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
            showError("Failed to add record. Please try again.");
        });

        new Thread(task).start();
    }

    
    private void clearForm() {
        diagnosisComoBox.setValue(null);
        txtPrescriptions.clear();
        txtNotes.clear();
        txtInstructions.clear();
    }


   @FXML

    private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DoctorDashboardController());
    }

    @FXML
    private void handlePatientQueue(ActionEvent event) {
        
       SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_queue.fxml", new Patient_queueController());
    }

    private void handleAddRecord(ActionEvent event) {
        System.out.println("Already on Add Record page. ");
    }

    @FXML
    private void handleEditRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/edit_record.fxml", new Edit_recordController());
    }

    @FXML
    private void onAddRecord(ActionEvent event) {
        addRecord();
    }

    @FXML
    private void onClearForm(ActionEvent event) {
        clearForm();
    }
}
